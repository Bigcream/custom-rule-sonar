package com.example.customsonarrule;

import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.semantic.Symbol;
import org.sonar.plugins.java.api.tree.MethodInvocationTree;
import org.sonar.plugins.java.api.tree.Tree;

import java.util.Collections;
import java.util.List;

@Rule(
        key = "NoSystemOutPrint",
        name = "Avoid using System.out.println",
        description = "System.out.println should not be used in production code."
)
public class NoSystemOutPrintRule extends IssuableSubscriptionVisitor {

    public static final String RULE_KEY = "NoSystemOutPrint";

    @Override
    public List<Tree.Kind> nodesToVisit() {
        return Collections.singletonList(Tree.Kind.METHOD_INVOCATION);
    }

    @Override
    public void visitNode(Tree tree) {
        MethodInvocationTree mit = (MethodInvocationTree) tree;
        Symbol methodSymbol = mit.methodSymbol();

        if (methodSymbol.isMethodSymbol()) {
            if ("println".equals(methodSymbol.name())) {
                Symbol owner = methodSymbol.owner();
                if (owner != null && "out".equals(owner.name())) {
                    Symbol ownersOwner = owner.owner();
                    if (ownersOwner != null && "System".equals(ownersOwner.name())) {
                        reportIssue(mit, "Replace System.out.println with a proper logging framework.");
                    }
                }
            }
        }
    }
}