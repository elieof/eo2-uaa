package com.fahkap.eo2.uaa;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.fahkap.eo2.uaa");

        noClasses()
            .that()
                .resideInAnyPackage("com.fahkap.eo2.uaa.service..")
            .or()
                .resideInAnyPackage("com.fahkap.eo2.uaa.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..com.fahkap.eo2.uaa.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
