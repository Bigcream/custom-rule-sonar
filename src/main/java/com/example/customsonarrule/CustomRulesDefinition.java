package com.example.customsonarrule;

import org.sonar.api.rules.RuleType;
import org.sonar.api.server.rule.RulesDefinition;

import javax.print.attribute.standard.Severity;

class CustomRulesDefinition implements RulesDefinition {
    private static final String REPOSITORY_KEY = "customjavarules";

    @Override
    public void define(Context context) {
        NewRepository repository = context.createRepository(REPOSITORY_KEY, "java")
                .setName("Custom Java Rules");

        NewRule rule = repository.createRule(NoSystemOutPrintRule.RULE_KEY)
                .setName("Avoid using System.out.println")
                .setHtmlDescription("System.out.println should not be used in production code; use a logger instead.")
                .setType(RuleType.VULNERABILITY)
                .setSeverity("CRITICAL");

        repository.done();
    }
}
