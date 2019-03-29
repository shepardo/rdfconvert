package org.example.rdfconvert;


import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.*;
import org.apache.jena.sparql.vocabulary.FOAF;




import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.w3c.dom.Document;
import org.w3c.dom.DOMException;

// For write operation
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.*;
import java.io.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        // test4();
        //*
        doXslt("/Users/fernando.gonzalez/Downloads/xml2rdf3.xsl", 
                //"/Users/fernando.gonzalez/Documents/src/pulsar/input1.xml");
                //"/Users/fernando.gonzalez/Documents/src/pulsar/signals/acquisition/DJMergerAcquisition_M_20190201.xml");
                "/Users/fernando.gonzalez/Documents/src/pulsar/signals/change/DJManagementChange_M_20190201.xml");
        //*/
    }
    
    public static void doXslt(String stylesheetFile, String dataFile) {
        try {
            File stylesheet = new File(stylesheetFile);
            File datafile = new File(dataFile);
            
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(datafile);
            // ...
            StreamSource stylesource = new StreamSource(stylesheet);
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer(stylesource);
            DOMSource source = new DOMSource(document);
            FileOutputStream fout = new FileOutputStream("/Users/fernando.gonzalez/Documents/src/pulsar/test/rdf-convert/output3.rdf");
            StreamResult result = new StreamResult(fout);
            transformer.transform(source, result);
            fout.flush();
            fout.close();
            
        } catch(Exception e) {
            System.out.println(e.getMessage() + "\n");
            e.printStackTrace();
        }
    }
    
    
    public static void test4()
    {
        Model model = ModelFactory.createDefaultModel();
        
        String ns = "http://somewhere4/";
        Property propName = model.createProperty(ns + "hasName");
        Property propValue = model.createProperty(ns + "hasValue");
        // Property propEntity = model.createProperty(ns + "");
        Property propSignalDocs = model.createProperty(ns + "SignalDocs");
        Property propActiveSignals = model.createProperty(ns + "ActivitySignals");
        Property propActiveSignalsList = model.createProperty(ns + "ActivitySignalsList");        
        Property propEntityList = model.createProperty(ns + "EntityList");
        Property propEntities = model.createProperty(ns + "Entities");
        Property propMasterSignals = model.createProperty(ns + "MasterSignals");
        Property propProperties = model.createProperty(ns + "Properties");
        Property propMsId = model.createProperty(ns + "hasId");
        Property propArticleHeadline = model.createProperty(ns + "Headline");
        Property propArticleDetection = model.createProperty(ns + "Detection");
        Property propArticlePhrases = model.createProperty(ns + "Phrases");
        Property propArticleKeywords = model.createProperty(ns + "Keywords");
        Property propArticlePhrase = model.createProperty(ns + "Phrase");
        Property propArticleKeyword = model.createProperty(ns + "Keyword");
        Property propArticleCompanies = model.createProperty(ns + "Companies");
        Property propArticleCompanyName = model.createProperty(ns + "CompanyName");
        Property propArticleCompanyPropertyValue = model.createProperty(ns + "PropertyValue");
        
        
        Resource signalList = model.createResource(ns + "SignalList");
        // RDFList list = model.createList();
        Resource signalDoc = model.createResource(ns + "SignalDoc");
        RDFList list = model.createList(signalDoc);
        signalList.addProperty(propSignalDocs, list);
        // list.cons(signalDoc);
        
        signalDoc.addProperty(propName, "myXName");
        
        Resource signalDoc2 = model.createResource(ns + "SignalDoc2");
        signalDoc2.addProperty(propName, "myXName2");
        list.add(signalDoc2);
        
                
        RDFList entityList = model.createList();
        Resource entityListList = model.createResource(ns + "EntityList");
        entityListList.addProperty(propEntityList, entityList);
        Resource entity = model.createResource(ns + "Entity");
        
        entityList.cons(entity);
        signalDoc.addProperty(propEntities, entityListList);
        
        
        RDFList activeSignalsList = model.createList();
        Resource activeSignalsListList = model.createResource(ns + "activeSignalLists");
        activeSignalsListList.addProperty(propActiveSignalsList, activeSignalsList);
        entity.addProperty(propActiveSignals, activeSignalsListList);
        
        Resource activeSignal = model.createResource(ns + "ActiveSignal");
        activeSignalsList.cons(activeSignal);
        
        Resource masterSignal = model.createResource(ns + "MasterSignal");
        RDFList masterSignalList = model.createList();
        activeSignal.addProperty(propMasterSignals, masterSignalList);
        masterSignalList.cons(masterSignal);
        masterSignal.addLiteral(propMsId, "17315141");
        
        RDFList propertyList = model.createList();
        Resource property = model.createResource(ns + "Property");
        property.addProperty(propName, "Organization");
        property.addProperty(propValue, "Wimmera Footbal League");
        propertyList.cons(property);
        
        Resource property2 = model.createResource(ns + "Property");
        property2.addProperty(propName, "Status");
        property2.addProperty(propValue, "Rumored");
        propertyList.cons(property2);
        
        RDFList articleList = model.createList();
        
        //Resource article = model.createResource(ns + "Article");
        //article.
        
        RDFList companyList = model.createList();
        
        model.write(System.out);
    }
    
    public static void test2()
    {
        Model model = ModelFactory.createDefaultModel();
        
        String ns = "http://somewhere/";
        
        
        Resource signalList = model.createResource(ns + "SignalList");
        // RDFList list = model.createList();
        Resource signalDoc = model.createResource(ns + "SignalDoc");
        RDFList list = model.createList(signalDoc);
        signalList.addProperty(model.createProperty(ns + "SignalDocs") , list);
        // list.cons(signalDoc);
        Property propName = model.createProperty(ns + "hasName");
        signalDoc.addProperty(propName, "myXName");
        
        Resource signalDoc2 = model.createResource(ns + "SignalDoc2");
        signalDoc2.addProperty(propName, "myXName2");
        list.cons(signalDoc2);
        
        //Resource signalList = model.createResource(signalListUri);
        //model.createList(new RDFNode[] { null });
        
        model.write(System.out);
    }
    
    public static void test3() {
        Model model = ModelFactory.createDefaultModel();
        
        model.createResource("http://example.org/alice", FOAF.Person)
            .addProperty(FOAF.name, "Alice")
            .addProperty(FOAF.mbox, model.createResource("mailto:alice@example.org"))
            .addProperty(FOAF.knows, model.createResource("http://example.org/bob"));

        model.write(System.out);
        // model.write(System.out, "TURTLE");
    }
    
    public static void test1() {
        // some definitions
        String personURI    = "http://somewhere/JohnSmith";
        String givenName    = "John";
        String familyName   = "Smith";
        String fullName     = givenName + " " + familyName;

        // create an empty Model
        Model model = ModelFactory.createDefaultModel();

        // create the resource
        //   and add the properties cascading style
        Resource johnSmith
          = model.createResource(personURI)
                 .addProperty(VCARD.FN, fullName)
                 .addProperty(VCARD.N,
                              model.createResource()
                                   .addProperty(VCARD.Given, givenName)
                                   .addProperty(VCARD.Family, familyName));
        model.write(System.out);
    }
}
