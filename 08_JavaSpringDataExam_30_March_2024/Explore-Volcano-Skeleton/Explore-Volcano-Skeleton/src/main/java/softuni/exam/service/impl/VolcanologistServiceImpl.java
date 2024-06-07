package softuni.exam.service.impl;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.VolcanologistRootDto;
import softuni.exam.models.dto.VolcanologistSeedDto;
import softuni.exam.models.entity.Volcano;
import softuni.exam.models.entity.Volcanologist;
import softuni.exam.repository.VolcanoRepository;
import softuni.exam.repository.VolcanologistRepository;
import softuni.exam.service.VolcanoService;
import softuni.exam.service.VolcanologistService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class VolcanologistServiceImpl implements VolcanologistService {
    private static String FILE_VOLCANOLOGISTS_PATH = "src/main/resources/files/xml/volcanologists.xml";

    private final VolcanologistRepository volcanologistRepository;
    private final VolcanoRepository volcanoRepository;
    private final VolcanoService volcanoService;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public VolcanologistServiceImpl(VolcanologistRepository volcanologistRepository, VolcanoRepository volcanoRepository, VolcanoService volcanoService, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.volcanologistRepository = volcanologistRepository;
        this.volcanoRepository = volcanoRepository;
        this.volcanoService = volcanoService;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.volcanologistRepository.count() > 0;
    }

    @Override
    public String readVolcanologistsFromFile() throws IOException {
        return Files.readString(Path.of(FILE_VOLCANOLOGISTS_PATH));
    }

    @Override
    public String importVolcanologists() throws IOException, JAXBException {
        StringBuilder importVolcanologists = new StringBuilder();

        VolcanologistRootDto volcanologistRootDto = xmlParser.fromFile(VolcanologistRootDto.class, FILE_VOLCANOLOGISTS_PATH);
        for (VolcanologistSeedDto volcanologistSeedDto : volcanologistRootDto.getVolcanologistSeedDtoList()) {

            Optional<Volcanologist> volcanologistOptional = volcanologistRepository.findAllByFirstNameAndLastName(
                    volcanologistSeedDto.getFirstName(), volcanologistSeedDto.getLastName());

            Optional<Volcano> volcanoOptional = volcanoRepository.findById(volcanologistSeedDto.getVolcano());


            if (!this.validationUtil.isValid(volcanologistSeedDto) || volcanologistOptional.isPresent() || volcanoOptional.isEmpty()) {
                importVolcanologists.append("Invalid volcanologist\n");
                continue;
            }

            Volcanologist volcanologist = this.modelMapper.map(volcanologistSeedDto, Volcanologist.class);
            volcanologist.setVolcano(volcanoOptional.get());
            this.volcanologistRepository.saveAndFlush(volcanologist);

            importVolcanologists.append(String.format("Successfully imported volcanologist %s %s%n",
                    volcanologist.getFirstName(), volcanologist.getLastName()));
        }
        return importVolcanologists.toString();
    }
}