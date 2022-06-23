package de.hhn.seb.labsw.transparentcockpit.backend.startup;

import de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.group.Section;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.input.DataType;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.base.input.InputType;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.template.ProjectReportTemplate;
import de.hhn.seb.labsw.transparentcockpit.backend.models.project.template.input.InputTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Default ProjectReportTemplate of Stadt Heilbronn.
 */
public class DefaultProjectReportTemplate {

    private final ProjectReportTemplate projectReportTemplate;


    public DefaultProjectReportTemplate() {
        projectReportTemplate = new ProjectReportTemplate(UUID.randomUUID());

        sectionA();
        sectionB();
        sectionC();
        sectionD();
        sectionE();
        sectionF();
        sectionG();
    }


    public ProjectReportTemplate getProjectReportTemplate() {
        return projectReportTemplate;
    }

    private void sectionA() {
        Section sectionA = new Section("A.", "Vorhabenauftrag");

        /* Vorhabens name existiert im ProjektReportTemplate
        Set<InputModifier> modifiersInputA1 = new HashSet<>();
        InputTemplate inputA1 = new InputTemplate("1.", "Vorhabentitle", true,
                DataType.STRING, InputType.SINGLE_INPUT);
        sectionA.addField(inputA1);*/

        List<Object> allowedValuesInputA2 = new ArrayList<>();//TODO auslesen der werte aus datei
        Collections.addAll(allowedValuesInputA2, "Dezernat_I", "101", "102", "103", "104", "105", "106", "107", "14", "30",
                "37", "GPR", "Dezernat_II", "10", "20", "23", "Dezernat_III", "32", "33", "40", "40.42", "40.43", "40.44", "40.45",
                "40.47", "46", "50", "53", "Dezernat_IV", "60", "62", "63", "65", "66", "67", "68", "70", "Stadtsiedlung Heilbronn GmbH",
                "Heilbronn Marketing GmbH (HMG)", "Beteiligungsgesellschaft Stadt Heilbronn mbH", "Wirtschaftsregion Heilbronn-Franken GmbH (WHF)",
                "SLK Kliniken Heilbronn GmbH", "Regionale Gesundheitsholding Heilbronn Franken GmbH (RGHF)", "Bundesgartenschau Heilbronn 2019 GmbH (BuGa)",
                "experimenta – Science Center der Region Heilbronn-Franken gGmbH", "Stadtwerke Heilbronn GmbH (SWH)", "Heilbronner Versorgungs GmbH (HVG)",
                "Heilbronner-Hohenloher-Haller-Nahverkehr GmbH (HNV)", "Wirtschaftsförderung Raum Heilbronn GmbH (WFG)", "Katharinenstift Heilbronn gGmbH",
                "Volkshochschule Heilbronn gGmbH", "EE Bürgerenergie Heilbronn GmbH & Co. KG", "Energieagentur Heilbronn GmbH");
        InputTemplate inputA2 = new InputTemplate("2.", "Federführendes Amt",
                true, DataType.STRING, InputType.SELECT_SINGLE_INPUT, allowedValuesInputA2);
        sectionA.addField(inputA2);

        InputTemplate inputA3 = new InputTemplate("3.", "Federführende Abteilung",
                true, DataType.STRING, InputType.SINGLE_INPUT);
        sectionA.addField(inputA3);

        InputTemplate inputA4 = new InputTemplate("4.", "Ansprechpartner*in", true,
                DataType.STRING, InputType.SINGLE_INPUT);
        sectionA.addField(inputA4);

        List<Object> allowedValuesInputA5 = new ArrayList<>();
        Collections.addAll(allowedValuesInputA5, "Maßnahme (langfristig)", "Maßnahme (mittelfristig)", "Maßnahme (kurzfristig)",
                "Reallabor", "noch undefiniert");
        InputTemplate inputA5 = new InputTemplate("5.", "Vorhabentyp",
                true, DataType.STRING, InputType.SELECT_SINGLE_INPUT, allowedValuesInputA5);
        sectionA.addField(inputA5);

        /*existiert in PorjektReportTemplate
        InputTemplate inputA6 = new InputTemplate("6.", "Vorhabennummer", true,
                DataType.STRING, InputType.SINGLE_INPUT);
        sectionA.addField(inputA6);//T
        */
        List<Object> allowedValuesInputA7 = new ArrayList<>();
        Collections.addAll(allowedValuesInputA7, "Eigenmotivation", "gesetzliches Erfordernis", "Auftrag", "Fördermittel/-Wettbewerb",
                "anderer Grund");
        InputTemplate inputA7 = new InputTemplate("7.", "Vorhabennummer",
                true, DataType.STRING, InputType.SELECT_SINGLE_INPUT, allowedValuesInputA7);
        sectionA.addField(inputA7);

        InputTemplate inputA71 = new InputTemplate("7.1", "Gesetz, Beschluss, etc. hier  angeben:",
                true, DataType.STRING, InputType.SINGLE_INPUT);
        sectionA.addField(inputA71);

        projectReportTemplate.addSection(sectionA);
    }

    private void sectionB() {
        Section sectionB = new Section("B.", "Vorhabeninhalt und Ziele");

        InputTemplate inputB1 = new InputTemplate("1.", "Beschreibung", false,
                DataType.STRING, InputType.SINGLE_INPUT);
        sectionB.addField(inputB1);

        InputTemplate inputB2 = new InputTemplate("2.", "Vorhabenziel", false,
                DataType.STRING, InputType.SINGLE_INPUT);
        sectionB.addField(inputB2);

        List<Object> allowedValuesInputB3 = new ArrayList<>();
        Collections.addAll(allowedValuesInputB3, "Dezernat_I", "101", "102", "103", "104", "105", "106", "107", "14", "30",
                "37", "GPR", "Dezernat_II", "10", "20", "23", "Dezernat_III", "32", "33", "40", "40.42", "40.43", "40.44", "40.45",
                "40.47", "46", "50", "53", "Dezernat_IV", "60", "62", "63", "65", "66", "67", "68", "70", "Stadtsiedlung Heilbronn GmbH",
                "Heilbronn Marketing GmbH (HMG)", "Beteiligungsgesellschaft Stadt Heilbronn mbH", "Wirtschaftsregion Heilbronn-Franken GmbH (WHF)",
                "SLK Kliniken Heilbronn GmbH", "Regionale Gesundheitsholding Heilbronn Franken GmbH (RGHF)", "Bundesgartenschau Heilbronn 2019 GmbH (BuGa)",
                "experimenta – Science Center der Region Heilbronn-Franken gGmbH", "Stadtwerke Heilbronn GmbH (SWH)", "Heilbronner Versorgungs GmbH (HVG)",
                "Heilbronner-Hohenloher-Haller-Nahverkehr GmbH (HNV)", "Wirtschaftsförderung Raum Heilbronn GmbH (WFG)", "Katharinenstift Heilbronn gGmbH",
                "Volkshochschule Heilbronn gGmbH", "EE Bürgerenergie Heilbronn GmbH & Co. KG", "Energieagentur Heilbronn GmbH");
        InputTemplate inputB3 = new InputTemplate("3.", "interne Projektbeteiligte",
                false, DataType.STRING, InputType.SELECT_MULTI_INPUT, allowedValuesInputB3);
        sectionB.addField(inputB3);

        InputTemplate inputB4 = new InputTemplate("4.", "externe Projektbeteiligte", false,
                DataType.STRING, InputType.MULTI_INPUT);
        sectionB.addField(inputB4);

        projectReportTemplate.addSection(sectionB);
    }

    private void sectionC() {
        Section sectionC = new Section("C.", "Vorhabenkontext");
        /*existiert in ProjektReportTemplate
        List<Object> allowedValuesInputC0 = new ArrayList<>();
        Collections.addAll(allowedValuesInputC0, "Verwaltung & Infrastruktur", "Bildungs- und Wissensstadt",
                "Teilhabe an der Stadtgesellschaft", "Zukunftsfähige Mobilität");
        InputTemplate inputC0 = new InputTemplate("0.", "Gruppe",true,
                DataType.STRING, InputType.SELECT_SINGLE_INPUT, allowedValuesInputC0);
        sectionC.addField(inputC0);
*/
        List<Object> allowedValuesInputC1A = new ArrayList<>();
        Collections.addAll(allowedValuesInputC1A, "vorrangig unterstürtzt", "grundsätzlich unterstützt", "nicht unterstützt");
        InputTemplate inputC1A = new InputTemplate("1.a",
                "Strategiefelder: Verwaltung & Infrastruktur", true, DataType.STRING, InputType.SELECT_SINGLE_INPUT, allowedValuesInputC1A);
        sectionC.addField(inputC1A);

        List<Object> allowedValuesInputC1B = new ArrayList<>();
        Collections.addAll(allowedValuesInputC1B, "vorrangig unterstürtzt", "grundsätzlich unterstützt", "nicht unterstützt");
        InputTemplate inputC1B = new InputTemplate("1.b",
                "Strategiefelder: Bildungs- und Wissensstadt", true, DataType.STRING, InputType.SELECT_SINGLE_INPUT, allowedValuesInputC1B);
        sectionC.addField(inputC1B);


        List<Object> allowedValuesInputC1C = new ArrayList<>();
        Collections.addAll(allowedValuesInputC1C, "vorrangig unterstürtzt", "grundsätzlich unterstützt", "nicht unterstützt");
        InputTemplate inputC1C = new InputTemplate("1.c",
                "Strategiefelder: Teilhabe an der Stadtgesellschaft", true, DataType.STRING, InputType.SELECT_SINGLE_INPUT,
                allowedValuesInputC1C);
        sectionC.addField(inputC1C);

        List<Object> allowedValuesInputC1D = new ArrayList<>();
        Collections.addAll(allowedValuesInputC1D, "vorrangig unterstürtzt", "grundsätzlich unterstützt", "nicht unterstützt");
        InputTemplate inputC1D = new InputTemplate("1.d",
                "Strategiefelder: Zukunftsfähige Mobilität", true, DataType.STRING, InputType.SELECT_SINGLE_INPUT, allowedValuesInputC1D);
        sectionC.addField(inputC1D);

        List<Object> allowedValuesInputC2 = new ArrayList<>();
        Collections.addAll(allowedValuesInputC2, "Ja", "Nein", "Noch in Klärung");
        InputTemplate inputC2 = new InputTemplate("2",
                "Vorhaben ist beteiligungspflichtig nach Landespersonal-Vertretungsgesetz", true,
                DataType.STRING, InputType.SELECT_SINGLE_INPUT, allowedValuesInputC2);
        sectionC.addField(inputC2);

        projectReportTemplate.addSection(sectionC);
    }

    private void sectionD() {
        Section sectionD = new Section("D.", "Vorhaben-Status");

        List<Object> allowedValuesInputD1 = new ArrayList<>();
        Collections.addAll(allowedValuesInputD1, "Im Plan", "Teilweise kritisch", "Kritisch");
        InputTemplate inputD1 = new InputTemplate("1.", "Gesamtstatus",
                false, DataType.STRING, InputType.SELECT_SINGLE_INPUT, allowedValuesInputD1);
        sectionD.addField(inputD1);

        List<Object> allowedValuesInputD2 = new ArrayList<>();
        Collections.addAll(allowedValuesInputD2, 0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100);
        InputTemplate inputD2 = new InputTemplate("2.", "Gesamtfortschritt",
                false, DataType.INTEGER, InputType.SELECT_SINGLE_INPUT, allowedValuesInputD2);
        sectionD.addField(inputD2);

        List<Object> allowedValuesInputD3 = new ArrayList<>();
        Collections.addAll(allowedValuesInputD3, "Ideenfindung", "Vorbereitung", "Planung", "Umsetzung", "Abgeschlossen", "Zurückgestellt");
        InputTemplate inputD3 = new InputTemplate("3.", "Bearbeitungsstand",
                false, DataType.STRING, InputType.SELECT_SINGLE_INPUT, allowedValuesInputD3);
        sectionD.addField(inputD3);

        projectReportTemplate.addSection(sectionD);
    }

    private void sectionE() {
        Section sectionE = new Section("E.", "Umsetzungszeitraum");

        InputTemplate inputE1 = new InputTemplate("1.", "geplanter Zeitpunkt für Vorhabenbeginn",
                false, DataType.DATE, InputType.SINGLE_INPUT);
        sectionE.addField(inputE1);

        InputTemplate inputE2 = new InputTemplate("2.",
                "ursprünglich geplanter Zeitpunkt für Vorhabenende", false, DataType.DATE, InputType.SINGLE_INPUT);
        sectionE.addField(inputE2);

        InputTemplate inputE3 = new InputTemplate("3.",
                "ursprünglich geplanter Zeitpunkt für Vorhabenende", false, DataType.DATE, InputType.SINGLE_INPUT);
        sectionE.addField(inputE3);

        projectReportTemplate.addSection(sectionE);
    }

    private void sectionF() {

        Section sectionF = new Section("F.", "Ressourcen");

        InputTemplate inputF1 = new InputTemplate("1.", "Kosten, soweit bezifferbar in EUR",
                false, DataType.INTEGER, InputType.SINGLE_INPUT);
        sectionF.addField(inputF1);

        List<Object> allowedValuesInputF2 = new ArrayList<>();
        allowedValuesInputF2.add("Ja");
        allowedValuesInputF2.add("Nein");
        InputTemplate inputF2 = new InputTemplate("2.",
                "Mittel im aktuellen HH enthalten?", false, DataType.STRING, InputType.SELECT_SINGLE_INPUT, allowedValuesInputF2);
        sectionF.addField(inputF2);

        List<Object> allowedValuesInputF3 = new ArrayList<>();
        allowedValuesInputF3.add("Ja");
        allowedValuesInputF3.add("Nein");
        InputTemplate inputF3 = new InputTemplate("3.",
                "Mittel für neuen HH beantragt?", false, DataType.STRING, InputType.SELECT_SINGLE_INPUT, allowedValuesInputF3);
        sectionF.addField(inputF3);

        projectReportTemplate.addSection(sectionF);
    }

    private void sectionG() {

        Section sectionG = new Section("G.", "Sonstige Angaben");

        List<Object> allowedValuesInputG1 = new ArrayList<>();
        allowedValuesInputG1.add("Ja");
        allowedValuesInputG1.add("Nein");
        InputTemplate inputG1 = new InputTemplate("1.", "Benötigt die Aktivität "
                + "später eine Anbindung an das Kassensystem, bzw. ist ein Bezahlsystem beabsichtigt?",
                false, DataType.STRING, InputType.SELECT_SINGLE_INPUT, allowedValuesInputG1);
        sectionG.addField(inputG1);

        List<Object> allowedValuesInputG2 = new ArrayList<>();
        allowedValuesInputG2.add("Ja");
        allowedValuesInputG2.add("Nein");
        InputTemplate inputG2 = new InputTemplate("2.",
                "Gibt es Redundanzen zu einem anderen Projekt?", false, DataType.STRING, InputType.SELECT_SINGLE_INPUT, allowedValuesInputG2);
        sectionG.addField(inputG2);

        InputTemplate inputG3 = new InputTemplate("3.", "Nennung des Projekts", false,
                DataType.STRING, InputType.SINGLE_INPUT);
        sectionG.addField(inputG3);

        List<Object> allowedValuesInputG4 = new ArrayList<>();
        allowedValuesInputG4.add("Ja");
        allowedValuesInputG4.add("Nein");
        InputTemplate inputG4 = new InputTemplate("4.",
                "Gibt es Synergie-effekte in Verbindung mit einem anderen Projekt?", false, DataType.STRING, InputType.SELECT_SINGLE_INPUT,
                allowedValuesInputG4);
        sectionG.addField(inputG4);

        InputTemplate inputG5 = new InputTemplate("5.", "Nennung des Projekts", false,
                DataType.STRING, InputType.SINGLE_INPUT);
        sectionG.addField(inputG5);

        List<Object> allowedValuesInputG6A = new ArrayList<>();
        Collections.addAll(allowedValuesInputG6A, "vorrangig unterstürtzt", "grundsätzlich unterstützt", "nicht unterstützt");
        InputTemplate inputG6A = new InputTemplate("6.a",
                "Wirkung des Vorhabens: Reduzierung des Verwaltungsaufwands (z.B. Personalverwaltung)", false,
                DataType.STRING, InputType.SELECT_SINGLE_INPUT, allowedValuesInputG6A);
        sectionG.addField(inputG6A);

        List<Object> allowedValuesInputG6B = new ArrayList<>();
        Collections.addAll(allowedValuesInputG6B, "vorrangig unterstürtzt", "grundsätzlich unterstützt", "nicht unterstützt");
        InputTemplate inputG6B = new InputTemplate("6.b",
                "Wirkung des Vorhabens: Reduzierung der Gemeinkosten durch gemeinsame Verwendung von Infrastruktur "
                        + "(z.B. IT)", false, DataType.STRING, InputType.SELECT_SINGLE_INPUT, allowedValuesInputG6B);
        sectionG.addField(inputG6B);

        List<Object> allowedValuesInputG6C = new ArrayList<>();
        Collections.addAll(allowedValuesInputG6C, "vorrangig unterstürtzt", "grundsätzlich unterstützt", "nicht unterstützt");
        InputTemplate inputG6C = new InputTemplate("6.c",
                "Wirkung des Vorhabens: Raschere Umsetzung durch Zusammenführung des Know-hows", false,
                DataType.STRING, InputType.SELECT_SINGLE_INPUT, allowedValuesInputG6C);
        sectionG.addField(inputG6C);

        List<Object> allowedValuesInputG6D = new ArrayList<>();
        Collections.addAll(allowedValuesInputG6D, "vorrangig unterstürtzt", "grundsätzlich unterstützt", "nicht unterstützt");
        InputTemplate inputG6D = new InputTemplate("6.d",
                "Wirkung des Vorhabens: Möglichkeit zur Entwicklung neuer Serviceangebote oder Produkte", false,
                DataType.STRING, InputType.SELECT_SINGLE_INPUT, allowedValuesInputG6D);
        sectionG.addField(inputG6D);

        List<Object> allowedValuesInputG6E = new ArrayList<>();
        Collections.addAll(allowedValuesInputG6E, "vorrangig unterstürtzt", "grundsätzlich unterstützt", "nicht unterstützt");
        InputTemplate inputG6E = new InputTemplate("6.e",
                "Wirkung des Vorhabens: Möglichkeit zur Erhöhung des Kundennutzens", false, DataType.STRING,
                InputType.SELECT_SINGLE_INPUT, allowedValuesInputG6E);
        sectionG.addField(inputG6E);

        InputTemplate inputG7 = new InputTemplate("7.", "freie Anmerkungen", false,
                DataType.STRING, InputType.SINGLE_INPUT);
        sectionG.addField(inputG7);

        projectReportTemplate.addSection(sectionG);
    }

}
