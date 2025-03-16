package com.example.customsonarrule.checks;

import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.tree.ExpressionTree;
import org.sonar.plugins.java.api.tree.IdentifierTree;
import org.sonar.plugins.java.api.tree.MemberSelectExpressionTree;
import org.sonar.plugins.java.api.tree.Tree;

import java.util.Collections;
import java.util.List;

@Rule(
        key = "NoSystemOutPrint",
        name = "Avoid using System.out.println",
        description = "System.out.println should not be used in production code."
)
public class NoSystemOutPrintRule extends IssuableSubscriptionVisitor {
    @Override
    public List<Tree.Kind> nodesToVisit() {
        return Collections.singletonList(Tree.Kind.MEMBER_SELECT);
    }

    @Override
    public void visitNode(Tree tree) {
        MemberSelectExpressionTree mset = (MemberSelectExpressionTree) tree;
        String name = mset.identifier().name();

        if ("out".equals(name) && isSystem(mset.expression())) {
            reportIssue(tree, "Thay System.out.println bằng logging framework phù hợp.");
        } else if ("err".equals(name) && isSystem(mset.expression())) {
            reportIssue(tree, "Thay System.out.println bằng logging framework phù hợp.");
        }
    }

    private static boolean isSystem(ExpressionTree expression) {
        IdentifierTree identifierTree = null;
        if (expression.is(Tree.Kind.IDENTIFIER)) {
            identifierTree = (IdentifierTree) expression;
        } else if (expression.is(Tree.Kind.MEMBER_SELECT)) {
            identifierTree = ((MemberSelectExpressionTree) expression).identifier();
        }
        return identifierTree != null && "System".equals(identifierTree.name());
    }
}