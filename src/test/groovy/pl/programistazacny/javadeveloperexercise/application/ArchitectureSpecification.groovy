package pl.programistazacny.javadeveloperexercise.application


import com.tngtech.archunit.core.domain.JavaClasses
import com.tngtech.archunit.core.importer.ClassFileImporter
import com.tngtech.archunit.core.importer.ImportOption
import com.tngtech.archunit.lang.ArchRule
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition
import com.tngtech.archunit.library.Architectures
import spock.lang.Shared
import spock.lang.Specification

class ArchitectureSpecification extends Specification {

    @Shared
    JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("pl.programistazacny.javadeveloperexercise")

    def "domain classes should not be accessing classes from adapter or application"() {
        given:
        ArchRule rule = ArchRuleDefinition
                .noClasses().that().resideInAnyPackage("..domain..")
                .should().accessClassesThat().resideInAnyPackage("..adapter..", "..application..")

        expect:
        rule.check(importedClasses)
    }

    def "port should be an interface"() {
        given:
        ArchRule rule = ArchRuleDefinition
                .classes().that().resideInAPackage("..domain.port")
                .should().beInterfaces()

        expect:
        rule.check(importedClasses)
    }

    def "layers should have proper accesses"() {
        given:
        ArchRule rule = Architectures.layeredArchitecture()

                .layer("Domain").definedBy("..domain..")
                .layer("Adapter").definedBy("..adapter..")
                .layer("Application").definedBy("..application..")

                .whereLayer("Application").mayNotBeAccessedByAnyLayer()
                .whereLayer("Adapter").mayOnlyBeAccessedByLayers("Application")
                .whereLayer("Domain").mayOnlyBeAccessedByLayers("Adapter", "Application")

        expect:
        rule.check(importedClasses)
    }
}
