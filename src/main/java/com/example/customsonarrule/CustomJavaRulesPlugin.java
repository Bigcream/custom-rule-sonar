package com.example.customsonarrule;

import org.sonar.api.Plugin;

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