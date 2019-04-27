package com.tp.ia;

import static com.tp.ia.src.CambioMinimo.calcularCambioMinimo;
import com.tp.ia.src.CambioMinimoFuncionAptitud;
import java.io.File;
import org.jgap.Genotype;
import org.jgap.data.DataTreeBuilder;
import org.jgap.data.IDataCreators;
import org.jgap.xml.XMLDocumentBuilder;
import org.jgap.xml.XMLManager;
import org.w3c.dom.Document;

public class TpIA {

    public static void main(String[] args) throws Exception {
        int amount = 353;
        try {
//amount = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("El (Monto de dinero) debe ser un numero entero valido");
            System.exit(1);
        }

        if (amount < 1 || amount >= CambioMinimoFuncionAptitud.MAX_MONTO) {
            System.out.println("El monto de dinero debe estar entre 1 y " + (CambioMinimoFuncionAptitud.MAX_MONTO - 1) + ".");
        } else {
            calcularCambioMinimo(amount);
        }
    }

    public static void guardarPoblacion(Genotype Poblacion) throws Exception {
        DataTreeBuilder builder = DataTreeBuilder.getInstance();
        IDataCreators doc2 = builder.representGenotypeAsDocument(Poblacion);
// create XML document from generated tree
        XMLDocumentBuilder docbuilder = new XMLDocumentBuilder();
        Document xmlDoc = (Document) docbuilder.buildDocument(doc2);
        XMLManager.writeFile(xmlDoc, new File("PoblacionCambioMinimo.xml"));
    }
}
