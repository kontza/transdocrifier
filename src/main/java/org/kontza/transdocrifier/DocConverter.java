package org.kontza;


import com.spire.doc.Document;
import com.spire.doc.ToPdfParameterList;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@NoArgsConstructor
@Slf4j
public class DocConverter {
    public void convertDocumentToPdf(String inputFilePath, boolean embedFonts, boolean replaceThinSpace, boolean disableLinks) {
        log.info("Going to convert '{}'...", inputFilePath);
        Document doc = new Document(false);
        doc.loadFromFile(inputFilePath);
        log.info("  document loaded...");
        ToPdfParameterList ppl = new ToPdfParameterList();
        if (embedFonts) {
            ppl.isEmbeddedAllFonts(true);
            log.info("  fonts embedded...");
        }
        if (disableLinks) {
            ppl.setDisableLink(true);
            log.info("  links disabled...");
        }
        log.info("  configurations set...");
        var lastDot = inputFilePath.lastIndexOf('.');
        var outputFilename = "";
        if (lastDot > -1) {
            outputFilename = inputFilePath.substring(0, lastDot);
        }
        outputFilename = outputFilename + ".pdf";
        if (replaceThinSpace) {
            doc.replace("\u2009", " ", true, true);
            log.info("  Replacements done...");
        }
        doc.saveToFile(outputFilename, ppl);
        log.info("  document converted...");
    }
}