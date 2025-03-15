package com.example.customsonarrule;

import org.sonar.api.Plugin;
import org.sonar.api.rules.RuleType;
import org.sonar.api.server.rule.RulesDefinition;

import java.util.List;

public class CustomJavaRulesPlugin implements Plugin {
    @Override
    public void define(Context context) {
        // Đăng ký rules
        context.addExtensions(getExtensions());
    }

    private List<Object> getExtensions() {
        return List.of(
                CustomRulesDefinition.class,
                NoSystemOutPrintRule.class
        );
    }
}

class CustomRulesDefinition implements RulesDefinition {
    private static final String REPOSITORY_KEY = "custom-java-rules";

    @Override
    public void define(Context context) {
        NewRepository repository = context.createRepository(REPOSITORY_KEY, "java")
                .setName("Custom Java Rules");

        NewRule rule = repository.createRule(NoSystemOutPrintRule.RULE_KEY)
                .setName("Avoid using System.out.println")
                .setHtmlDescription("System.out.println should not be used in production code; use a logger instead.")
                .setType(RuleType.CODE_SMELL)
                .setSeverity("MAJOR");

        repository.done();
    }
}