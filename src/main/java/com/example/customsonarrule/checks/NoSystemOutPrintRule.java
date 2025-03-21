/*
 * SonarQube Java
 * Copyright (C) 2012-2024 SonarSource SA
 * mailto:info AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
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