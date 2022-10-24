package bss.queries;

import bss.db.DB;
import org.apache.calcite.adapter.java.ReflectiveSchema;
import org.apache.calcite.rel.RelNode;
import org.apache.calcite.rel.RelRoot;
import org.apache.calcite.schema.SchemaPlus;
import org.apache.calcite.sql.SqlNode;
import org.apache.calcite.sql.parser.SqlParser;
import org.apache.calcite.tools.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TestQueryBaseClass {
    static final FrameworkConfig frameworkConfig;
    static {
        SchemaPlus rootSchema = Frameworks.createRootSchema(true);
        ReflectiveSchema schema = new ReflectiveSchema(DB.create());
        SchemaPlus schemaPlus = rootSchema.add("root", schema);

        SqlParser.Config insensitiveParser = SqlParser.configBuilder()
                .setCaseSensitive(false)
        		.build();
        frameworkConfig = Frameworks.newConfigBuilder()
        		.parserConfig(insensitiveParser)
        		.defaultSchema(schemaPlus)
        		.build();
    }

    ResultSet run(String query) throws Exception {
        Planner planner = Frameworks.getPlanner(frameworkConfig);
        SqlNode sqlNode = planner.parse(query);
        SqlNode sqlNodeValidated = planner.validate(sqlNode);
        RelRoot relRoot = planner.rel(sqlNodeValidated);
        RelNode relNode = relRoot.project();
        PreparedStatement run = RelRunners.run(relNode);
        return run.executeQuery();
    }
}
