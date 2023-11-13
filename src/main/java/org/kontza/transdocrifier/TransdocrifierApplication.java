package org.kontza.transdocrifier;

import lombok.extern.slf4j.Slf4j;
import org.kontza.DocConverter;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Set;

@SpringBootApplication
@Slf4j
public class TransdocrifierApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(TransdocrifierApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        var optionNames = args.getOptionNames();
        if (optionNames.contains("help")) {
            log.info("--embed-fonts   = embed fonts in PDF (default: False)");
            log.info("--replace-space = replace thin space with regular ones (default: False)");
            log.info("--disable-links = disable links embedding (default: False)");
        }
        optionNames.forEach(s -> log.info("Option: {}", s));
        args.getNonOptionArgs().forEach(s -> log.info("Non-option: {}", s));
        for (String inputFile : args.getNonOptionArgs()) {
            new DocConverter().convertDocumentToPdf(
                    inputFile,
                    optionNames.contains("embed-fonts"),
                    optionNames.contains("replace-space"),
                    optionNames.contains("disable-links")
                    );
        }
    }
}
