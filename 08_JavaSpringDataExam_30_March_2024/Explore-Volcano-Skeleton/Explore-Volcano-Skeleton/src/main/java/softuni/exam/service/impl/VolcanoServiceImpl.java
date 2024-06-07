package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.VolcanoSeedDto;
import softuni.exam.models.entity.Country;
import softuni.exam.models.entity.Volcano;
import softuni.exam.repository.CountryRepository;
import softuni.exam.repository.VolcanoRepository;
import softuni.exam.service.VolcanoService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import java.util.Optional;
import java.util.Set;

@Service
public class VolcanoServiceImpl implements VolcanoService {
    private static final String FILE_VOLCANOES_PATH = "src/main/resources/files/json/volcanoes.json";
    private final VolcanoRepository volcanoRepository;
    private final CountryRepository countryRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public VolcanoServiceImpl(VolcanoRepository volcanoRepository, CountryRepository countryRepository, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.volcanoRepository = volcanoRepository;
        this.countryRepository = countryRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return this.volcanoRepository.count() > 0;
    }

    @Override
    public String readVolcanoesFileContent() throws IOException {
        return Files.readString(Path.of(FILE_VOLCANOES_PATH));
    }

    @Override
    public String importVolcanoes() throws IOException {
        StringBuilder importVolcanoes = new StringBuilder();
        VolcanoSeedDto[] volcanoSeedDtos = this.gson.fromJson(readVolcanoesFileContent(), VolcanoSeedDto[].class);


        for (VolcanoSeedDto volcanoSeedDto : volcanoSeedDtos) {
            Optional<Volcano> optional = this.volcanoRepository.findByName(volcanoSeedDto.getName());

            Country country = this.countryRepository.getById(volcanoSeedDto.getCountry());
            if(!this.validationUtil.isValid(volcanoSeedDto) || optional.isPresent()) {
                importVolcanoes.append("Invalid volcano\n");
                continue;
            }

            Volcano volcano = this.modelMapper.map(volcanoSeedDto, Volcano.class);
            volcano.setCountry(country);
            this.volcanoRepository.saveAndFlush(volcano);

            importVolcanoes.append(String.format("Successfully imported volcano %s of type %s\n",
                    volcano.getName(), volcano.getVolcanoType()));
        }

        return importVolcanoes.toString();
    }


    @Override
    public String exportVolcanoes() {
        StringBuilder export = new StringBuilder();

        Set<Volcano> volcanoesAbove3000 = volcanoRepository.findAllByVolcanoIsActiveAndAbove3000m();
        volcanoesAbove3000
                .forEach(volcano -> {
                    export.append(String.format("Volcano %s%n" +
                                            "*Located in: %s%n" +
                                            "**Elevation: %d%n" +
                                            "***Last eruption on: %s",
                                    volcano.getName(),
                                    volcano.getCountry().getName(),
                                    volcano.getElevation(),
                                    volcano.getLastEruption()))
                            .append(System.lineSeparator());
                });

        return export.toString().trim();
    }
}