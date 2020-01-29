package lifecycle;

import net.thucydides.core.guice.Injectors;
import net.thucydides.core.reports.html.HtmlAggregateStoryReporter;
import net.thucydides.core.util.EnvironmentVariables;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SerenityReports {

    public static void clean() {
        try {
            EnvironmentVariables ev = Injectors.getInjector().getInstance(EnvironmentVariables.class);
            String outputDirectory = ev.getProperty("serenity.outputDirectory");
            Path sourceDirectory = Paths.get(outputDirectory).toAbsolutePath();
            FileUtils.deleteDirectory(sourceDirectory.toFile());
        } catch (IOException e) {
            throw new RuntimeException("Could not clean reports");
        }
    }

    public static void generate() {
        try {
            EnvironmentVariables ev = Injectors.getInjector().getInstance(EnvironmentVariables.class);
            String outputDirectory = ev.getProperty("serenity.outputDirectory");
            Path sourceDirectory = Paths.get(outputDirectory).toAbsolutePath();
            HtmlAggregateStoryReporter reporter = new HtmlAggregateStoryReporter("hotelBookingTest");
            reporter.setSourceDirectory(sourceDirectory.toFile());
            reporter.setOutputDirectory(sourceDirectory.toFile());
            reporter.generateReportsForTestResultsFrom(sourceDirectory.toFile());
        } catch (IOException e) {
            throw new RuntimeException("Could not generate reports");
        }
    }
}
