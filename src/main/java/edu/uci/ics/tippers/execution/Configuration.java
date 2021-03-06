package edu.uci.ics.tippers.execution;

import edu.uci.ics.tippers.common.Database;
import edu.uci.ics.tippers.common.ReportFormat;
import edu.uci.ics.tippers.exception.BenchmarkException;

import java.util.*;
import java.util.prefs.Preferences;
import java.util.stream.Collectors;

public class Configuration {

    private List<Database> databases = new ArrayList<>();
    private Map<Database, List<Integer>> mappings = new HashMap<>();
    private Preferences preferences;
    private String scriptsDir;
    private String dataDir;
    private String queriesDir;
    private String reportsDir;
    private String outputDir;
    private boolean writeOutput;
    private ReportFormat format;
    private Long timeout;
    private boolean scaleQueries;
    private boolean scaleData;

    public Configuration(Preferences preferences) throws BenchmarkException {
        this.preferences = preferences;
        readPreferences();
    }

    public void readPreferences() throws BenchmarkException {

        // Reading Database List
        String nodeValue = preferences.node("benchmark").get("databases", null);
        if (nodeValue == null) {
            throw new BenchmarkException("Database list not provided in configuration file");
        }

        databases = Arrays.stream(nodeValue.split(",")).map(e->Database.get(e)).collect(Collectors.toList());

        // Reading Mapping List
        for(Database db: databases) {
            nodeValue = preferences.node(db.getName()).get("mapping", null);
            if (nodeValue == null) {
                throw new BenchmarkException("Mapping list not provided in configuration file");
            }
            mappings.put(db, Arrays.stream(nodeValue.split(","))
                    .map(e->Integer.parseInt(e)).collect(Collectors.toList()));
        }

        // Reading Directories
        nodeValue = preferences.node("benchmark").get("scripts-dir", null);
        if (nodeValue == null) {
            throw new BenchmarkException("Scripts directory location not provided in configuration file");
        }
        scriptsDir = nodeValue;

        nodeValue = preferences.node("benchmark").get("data-dir", null);
        if (nodeValue == null) {
            throw new BenchmarkException("Data directory location not provided in configuration file");
        }
        dataDir = nodeValue;

        nodeValue = preferences.node("benchmark").get("queries-dir", null);
        if (nodeValue == null) {
            throw new BenchmarkException("Queries directory location not provided in configuration file");
        }
        queriesDir = nodeValue;

        nodeValue = preferences.node("benchmark").get("reports-dir", null);
        if (nodeValue == null) {
            throw new BenchmarkException("Reports directory location not provided in configuration file");
        }
        reportsDir = nodeValue;

        nodeValue = preferences.node("benchmark").get("query-result-dir", null);
        if (nodeValue == null) {
            throw new BenchmarkException("Output directory location not provided in configuration file");
        }
        outputDir = nodeValue;

        writeOutput = preferences.node("benchmark").getBoolean("write-query-result", false);

        format= ReportFormat.get(preferences.node("benchmark").get("report-format", "text"));

        timeout= preferences.node("benchmark").getLong("query-timeout", 10000000);

        scaleData = preferences.node("benchmark").getBoolean("scale-data", false);

        scaleQueries = preferences.node("benchmark").getBoolean("scale-query", false);

    }

    public List<Database> getDatabases() {
        return databases;
    }

    public void setDatabases(List<Database> databases) {
        this.databases = databases;
    }

    public Map<Database, List<Integer>> getMappings() {
        return mappings;
    }

    public void setMappings(Map<Database, List<Integer>> mappings) {
        this.mappings = mappings;
    }

    public String getScriptsDir() {
        return scriptsDir;
    }

    public void setScriptsDir(String scriptsDir) {
        this.scriptsDir = scriptsDir;
    }

    public String getDataDir() {
        return dataDir;
    }

    public void setDataDir(String dataDir) {
        this.dataDir = dataDir;
    }

    public String getQueriesDir() {
        return queriesDir;
    }

    public void setQueriesDir(String queriesDir) {
        this.queriesDir = queriesDir;
    }

    public String getReportsDir() {
        return reportsDir;
    }

    public void setReportsDir(String reportsDir) {
        this.reportsDir = reportsDir;
    }

    public boolean isWriteOutput() {
        return writeOutput;
    }

    public void setWriteOutput(boolean writeOutput) {
        this.writeOutput = writeOutput;
    }

    public ReportFormat getFormat() {
        return format;
    }

    public void setFormat(ReportFormat format) {
        this.format = format;
    }

    public Long getTimeout() {
        return timeout;
    }

    public void setTimeout(Long timeout) {
        this.timeout = timeout;
    }

    public boolean isScaleQueries() {
        return scaleQueries;
    }

    public void setScaleQueries(boolean scaleQueries) {
        this.scaleQueries = scaleQueries;
    }

    public boolean isScaleData() {
        return scaleData;
    }

    public void setScaleData(boolean scaleData) {
        this.scaleData = scaleData;
    }

    public String getOutputDir() {
        return outputDir;
    }

    public void setOutputDir(String outputDir) {
        this.outputDir = outputDir;
    }
}
