package org.kfokam48.cliniquemanagementbackend.service.pdf;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfAppearance;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.kfokam48.cliniquemanagementbackend.model.Facture;
import org.kfokam48.cliniquemanagementbackend.model.Medecin;
import org.kfokam48.cliniquemanagementbackend.model.Patient;
import org.kfokam48.cliniquemanagementbackend.model.Prescription;

import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.time.format.DateTimeFormatter;


@Service
public class PdfService {

    public ByteArrayOutputStream generateFacturePdf(Facture facture) throws DocumentException {


        Document document = new Document();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter pdfWriter = PdfWriter.getInstance(document, outputStream);
        document.open();
        // Titre du document
        document.add(new Paragraph("Facture Médicale"));
        // Contenu de la facture
        document.add(new Paragraph("Facture"));
        document.add(new Paragraph("ID: " + facture.getId()));
        document.add(new Paragraph("Patient: " + facture.getPatient().getUsername() + " " + facture.getPatient().getPrenom()));
        document.add(new Paragraph("Montant: " + facture.getMontantTotal() + " €"));
        document.add(new Paragraph("Date Impression: " + facture.getDateEmission()));
        document.add(new Paragraph("Mode de paiement: " + (facture.getModePayement() != null ? facture.getModePayement().toString() : "Non spécifié")));
        document.add(new Paragraph("Statut de paiement: " + (facture.getStatutPayement() != null ? facture.getStatutPayement().toString() : "Non spécifié")));
        document.add(new Paragraph("Date Payement: " + facture.getDatePayement()));
        document.add(new Paragraph("Motif: " + facture.getDescription()));

        document.close();
        return outputStream;
    }

    public ByteArrayOutputStream generatePrescriptionPdf(Prescription prescription) throws FileNotFoundException, DocumentException {
        Patient patient = prescription.getPatient();
        Medecin medecin = prescription.getMedecin();
        Document document = new Document();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter pdfWriter = PdfWriter.getInstance(document, outputStream);

        //PdfWriter.getInstance(document, outputStream);
        document.open();

        // ✅ Ajout de l'en-tête de la clinique
        document.add(new Paragraph("######################################################################"));
        Paragraph entete = new Paragraph("CLINIC NAME");
        entete.setAlignment(Element.ALIGN_CENTER);
        entete.setFont(FontFactory.getFont(FontFactory.HELVETICA_BOLD, 30));
        document.add(entete);
        document.add(new Paragraph("Yaoundé , EMANA, CAMEROUN"));
        document.add(new Paragraph("youremail@companyname.com / yourwebsite.com"));
        document.add(new Paragraph("#######################################################################"));
        // Titre du document
        Paragraph title = new Paragraph("PRESCRIPTION MEDICALE");
        title.setAlignment(Element.ALIGN_CENTER);
        title.setFont(FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20));
        title.setSpacingAfter(10);
        title.setSpacingBefore(10);
        document.add(title);
        document.add(new Paragraph("========================================================================"));
        Paragraph info = new Paragraph();

        // Ajouter la date de prescription
        Phrase prescriptionDate = new Phrase("Prescription Date: " + prescription.getDatePrescription().format(DateTimeFormatter.ofPattern("MMMM d, yyyy")));
        info.add(prescriptionDate);

        // Ajouter le numéro de prescription
        Phrase prescriptionNumber = new Phrase("Prescription Number: " + prescription.getId());
        prescriptionNumber.setFont(FontFactory.getFont(FontFactory.HELVETICA, 14));
        info.add(new Chunk("         /           ")); // Ajouter un séparateur entre les deux informations
        info.add(prescriptionNumber);
        document.add(info);





        Paragraph subTitle = new Paragraph("PATIENT INFORMATIONS");
        title.setFont(FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16));
        subTitle.setSpacingAfter(5);
        subTitle.setSpacingBefore(15);
        subTitle.setAlignment(Element.ALIGN_CENTER);
        document.add(subTitle);
        // Ajouter les informations du patient
        Paragraph patientInfo = new Paragraph();
        patientInfo.add(new Paragraph("Nom: " + (patient.getUsername() !=null ? patient.getNom() : "N/A")));
        patientInfo.add(new Paragraph("Prénom: " + (patient.getPrenom() !=null ? patient.getPrenom() : "N/A")));
        patientInfo.add(new Paragraph(String.valueOf("Numéro de dossier médical: " + (!patient.getNumeroDossierMedical().toString().isEmpty() ? patient.getNumeroDossierMedical() : "N/A"))));
        patientInfo.add(new Paragraph("Phone Number: " +(patient.getTelephone() != null ? patient.getTelephone() : "N/A")));
        patientInfo.setAlignment(Element.ALIGN_LEFT);
        document.add(patientInfo);


        Paragraph patientInfo2 = new Paragraph();
        patientInfo2.add(new Paragraph("Date of Birth: "+ new Phrase(patient.getDateNaissance() != null ? patient.getDateNaissance().toString() : "N/A")));
        patientInfo2.add(new Paragraph("Email: " + (patient.getEmail() != null ? patient.getEmail() : "N/A")));
        patientInfo2.add(new Paragraph("Gender: "+ (patient.getSexe() != null ? patient.getSexe().toString() : "N/A")));
        patientInfo2.add(new Paragraph("Address: " + (patient.getAdresse() != null ? patient.getAdresse() : "N/A")));
        patientInfo2.add(new Paragraph("Allergies: " + (patient.getAllergies() != null ? patient.getAllergies().toString() : "N/A")));
        patientInfo2.setAlignment(Element.ALIGN_RIGHT);
        document.add(patientInfo2);

        // Ajouter la liste des médicaments prescrits
        document.add(new Paragraph(" "));
        Paragraph subTitle2 = new Paragraph("List of Medications Prescribed");
        subTitle2.setFont(FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16));
        subTitle2.setSpacingAfter(5);
        subTitle2.setAlignment(Element.ALIGN_CENTER);
        document.add(subTitle2);
        PdfPTable medicationTable = new PdfPTable(5);
        medicationTable.addCell(new PdfPCell(new Phrase("Medication Name")));
        medicationTable.addCell(new PdfPCell(new Phrase("Purpose")));
        medicationTable.addCell(new PdfPCell(new Phrase("Dosage")));
        medicationTable.addCell(new PdfPCell(new Phrase("Route")));
        medicationTable.addCell(new PdfPCell(new Phrase("Frequency")));

        // Exemple de médicaments
        medicationTable.addCell(new PdfPCell(new Phrase(prescription.getMedicaments())));
        medicationTable.addCell(new PdfPCell(new Phrase("Removes phlegm")));
        medicationTable.addCell(new PdfPCell(new Phrase("1 tablet")));
        medicationTable.addCell(new PdfPCell(new Phrase("Oral")));
        medicationTable.addCell(new PdfPCell(new Phrase(prescription.getInstructions() != null ? prescription.getInstructions() : "N/A")));
        // Ajouter les autres médicaments de la même manière
        document.add(medicationTable);

        // Ajouter les informations du médecin

        Paragraph subTitle3 = new Paragraph("MEDECIN INFORMATIONS");
        subTitle3.setFont(FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16));
        subTitle3.setSpacingAfter(5);
        subTitle3.setSpacingBefore(15);
        subTitle3.setAlignment(Element.ALIGN_CENTER);
        document.add(subTitle3);
        document.add(new Paragraph(" "));
        document.add(new Paragraph("Nom: " + (medecin.getNom()!=null ? medecin.getNom() : "N/A")));
        document.add(new Paragraph("Prénom: " + (medecin.getPrenom() !=null ? medecin.getPrenom() : "N/A")));
        document.add(new Paragraph("Phone Number: " + (medecin.getTelephone()!= null ? medecin.getTelephone() : "N/A")));
        document.add(new Paragraph("Email: " + (medecin.getEmail() != null ? medecin.getEmail() : "N/A")));
        document.add(new Paragraph("Address: " + (medecin.getAdresse()!=null ? medecin.getAdresse() : "N/A")));
        document.add(new Paragraph("Specialty: " + (medecin.getSpecialite() != null ? medecin.getSpecialite().toString() : "N/A")));
        document.add(new Paragraph(new Phrase("Physician Signature: [Signature]")));


//        PdfAppearance appearance = PdfAppearance.createAppearance(pdfWriter, 500, 200);
//        appearance.setColorFill(BaseColor.LIGHT_GRAY);

        document.close();
        return outputStream;
    }
}
