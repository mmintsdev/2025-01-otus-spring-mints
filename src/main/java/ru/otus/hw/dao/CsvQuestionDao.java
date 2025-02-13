package ru.otus.hw.dao;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.hw.config.FileNameTestProvider;
import ru.otus.hw.dao.dto.QuestionDto;
import ru.otus.hw.domain.Question;
import ru.otus.hw.exceptions.QuestionReadException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class CsvQuestionDao implements QuestionDao {

    private final FileNameTestProvider fileNameProvider;

    @Override
    public List<Question> findAll() {

        String fileName = fileNameProvider.getTestFileName();
        ClassLoader classLoader = getClass().getClassLoader();

        try (InputStream is = classLoader.getResourceAsStream(fileName)) {
            if (is == null) {
                throw new QuestionReadException("File not found: " + fileName);
            }
            return parseCsv(new InputStreamReader(is, StandardCharsets.UTF_8));
        } catch (IOException ex) {
            throw new QuestionReadException("Error while working with a file: " + fileName, ex);
        }
    }

    private List<Question> parseCsv(Reader reader) {
        ColumnPositionMappingStrategy<QuestionDto> strategy = new ColumnPositionMappingStrategy<>();
        strategy.setType(QuestionDto.class);

        List<QuestionDto> parsedList = new CsvToBeanBuilder<QuestionDto>(reader)
                .withExceptionHandler(e -> {
                    throw new QuestionReadException(
                            String.format("Error when reading from line %d: %s", e.getLineNumber(), e.getMessage()),
                            e  // Передаем оригинальное исключение как cause
                    );
                })
                .withMappingStrategy(strategy)
                .withSeparator(';')
                .withSkipLines(1)
                .build()
                .parse();

        return parsedList.stream()
                .filter(Objects::nonNull)
                .map(QuestionDto::toDomainObject)
                .collect(Collectors.toList());
    }
}