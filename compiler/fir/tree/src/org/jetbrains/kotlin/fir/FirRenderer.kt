/*
 * Copyright 2000-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license 
 * that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir

import org.jetbrains.kotlin.fir.declarations.*
import org.jetbrains.kotlin.fir.expressions.*
import org.jetbrains.kotlin.fir.types.*
import org.jetbrains.kotlin.fir.visitors.FirVisitorVoid
import org.jetbrains.kotlin.utils.Printer

class FirRenderer(builder: StringBuilder) : FirVisitorVoid() {
    private val printer = Printer(builder)

    private fun renderAnnotations(container: FirAnnotationContainer) {
        for (annotationCall in container.annotations) {
            annotationCall.accept(this)
        }
    }

    private fun renderDeclarations(container: FirDeclarationContainer) {
        for (declaration in container.declarations) {
            declaration.accept(this)
        }
    }

    override fun visitFile(file: FirFile) {
        printer.println("FILE") // TODO: file name
        printer.pushIndent()
        printer.println("package: " + file.packageFqName)
        renderAnnotations(file)
        visitPackageFragment(file)
        for (import in file.imports) {
            import.accept(this)
        }
        printer.popIndent()
    }

    override fun visitPackageFragment(packageFragment: FirPackageFragment) {
        renderDeclarations(packageFragment)
    }

    override fun visitClass(klass: FirClass) {
        renderAnnotations(klass)
        printer.print(klass.classKind.name.toLowerCase().replace('_', ' '))
    }

    override fun visitElement(element: FirElement) {
        
    }

    override fun visitDeclaration(declaration: FirDeclaration) {
        visitElement(declaration)
    }

    override fun visitCallableMember(callableMember: FirCallableMember) {
        visitDeclaration(callableMember)
    }

    override fun visitDeclarationWithBody(declarationWithBody: FirDeclarationWithBody) {
        visitDeclaration(declarationWithBody)
    }

    override fun visitAnonymousInitializer(anonymousInitializer: FirAnonymousInitializer) {
        visitDeclarationWithBody(anonymousInitializer)
    }

    override fun visitFunction(function: FirFunction) {
        visitDeclarationWithBody(function)
    }

    override fun visitConstructor(constructor: FirConstructor) {
        visitFunction(constructor)
    }

    override fun visitNamedFunction(namedFunction: FirNamedFunction) {
        visitFunction(namedFunction)
    }

    override fun visitPropertyAccessor(propertyAccessor: FirPropertyAccessor) {
        visitFunction(propertyAccessor)
    }

    override fun visitErrorDeclaration(errorDeclaration: FirErrorDeclaration) {
        visitDeclaration(errorDeclaration)
    }

    override fun visitNamedDeclaration(namedDeclaration: FirNamedDeclaration) {
        visitDeclaration(namedDeclaration)
    }

    override fun visitMemberDeclaration(memberDeclaration: FirMemberDeclaration) {
        visitNamedDeclaration(memberDeclaration)
    }

    override fun visitEnumEntry(enumEntry: FirEnumEntry) {
        visitClass(enumEntry)
    }

    override fun visitTypeAlias(typeAlias: FirTypeAlias) {
        visitMemberDeclaration(typeAlias)
    }

    override fun visitTypeParameter(typeParameter: FirTypeParameter) {
        visitNamedDeclaration(typeParameter)
    }

    override fun visitProperty(property: FirProperty) {
        visitDeclaration(property)
    }

    override fun visitTypedDeclaration(typedDeclaration: FirTypedDeclaration) {
        visitDeclaration(typedDeclaration)
    }

    override fun visitValueParameter(valueParameter: FirValueParameter) {
        visitDeclaration(valueParameter)
    }

    override fun visitVariable(variable: FirVariable) {
        visitDeclaration(variable)
    }

    override fun visitImport(import: FirImport) {
        visitElement(import)
    }

    override fun visitStatement(statement: FirStatement) {
        visitElement(statement)
    }

    override fun visitExpression(expression: FirExpression) {
        visitStatement(expression)
    }

    override fun visitBody(body: FirBody) {
        visitExpression(body)
    }

    override fun visitCall(call: FirCall) {
        visitExpression(call)
    }

    override fun visitAnnotationCall(annotationCall: FirAnnotationCall) {
        visitCall(annotationCall)
    }

    override fun visitConstructorCall(constructorCall: FirConstructorCall) {
        visitCall(constructorCall)
    }

    override fun visitType(type: FirType) {
        visitElement(type)
    }

    override fun visitDelegatedType(delegatedType: FirDelegatedType) {
        visitType(delegatedType)
    }

    override fun visitErrorType(errorType: FirErrorType) {
        visitType(errorType)
    }

    override fun visitImplicitType(implicitType: FirImplicitType) {
        visitType(implicitType)
    }

    override fun visitTypeWithNullability(typeWithNullability: FirTypeWithNullability) {
        visitType(typeWithNullability)
    }

    override fun visitDynamicType(dynamicType: FirDynamicType) {
        visitTypeWithNullability(dynamicType)
    }

    override fun visitFunctionType(functionType: FirFunctionType) {
        visitTypeWithNullability(functionType)
    }

    override fun visitResolvedType(resolvedType: FirResolvedType) {
        visitTypeWithNullability(resolvedType)
    }

    override fun visitUserType(userType: FirUserType) {
        visitTypeWithNullability(userType)
    }

    override fun visitTypeProjection(typeProjection: FirTypeProjection) {
        visitElement(typeProjection)
    }

    override fun visitStarProjection(starProjection: FirStarProjection) {
        visitTypeProjection(starProjection)
    }
    
}