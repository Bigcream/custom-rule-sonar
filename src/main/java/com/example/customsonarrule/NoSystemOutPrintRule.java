package com.example.customsonarrule;

import org.slf4j.Logger;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.semantic.Symbol;
import org.sonar.plugins.java.api.tree.MethodInvocationTree;
import org.sonar.plugins.java.api.tree.Tree;

import java.util.Collections;
import java.util.List;
import org.slf4j.LoggerFactory;

@Rule(
        key = "NoSystemOutPrint",
        name = "Avoid using System.out.println",
        description = "System.out.println should not be used in production code."
)
public class NoSystemOutPrintRule extends IssuableSubscriptionVisitor {
    private static final Logger LOGGER = LoggerFactory.getLogger(NoSystemOutPrintRule.class);
    public static final String RULE_KEY = "NoSystemOutPrint";


    @Override
    public List<Tree.Kind> nodesToVisit() {
        return Collections.singletonList(Tree.Kind.METHOD_INVOCATION);
    }

    @Override
    public void visitNode(Tree tree) {
        MethodInvocationTree mit = (MethodInvocationTree) tree;
        Symbol methodSymbol = mit.methodSymbol();

        LOGGER.info("Đang kiểm tra: {}", mit.toString());
        if (methodSymbol.isMethodSymbol()) {
            if ("println".equals(methodSymbol.name())) {
                Symbol owner = methodSymbol.owner();
                if (owner != null && "out".equals(owner.name())) {
                    Symbol ownersOwner = owner.owner();
                    if (ownersOwner != null && "System".equals(ownersOwner.name())) {
                        LOGGER.info("Phát hiện System.out.println tại: {}", mit);
                        reportIssue(mit, "Thay System.out.println bằng logging framework phù hợp.");
                    }
                }
            }
        }
    }
}