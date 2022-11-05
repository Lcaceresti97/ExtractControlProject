package com.sti.extractcontrolmodule.utils.report;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.sti.extractcontrolmodule.dto.ExtractedLogDto;
import com.sti.extractcontrolmodule.dto.QualityControllerLogDto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
public class ExtractReport {

    List<QualityControllerLogDto>qualityControllerLogDtos;


    public void export(HttpServletResponse response) throws IOException {
        Document document = new Document(PageSize.A4.rotate());
        PdfWriter.getInstance(document,response.getOutputStream());
        document.open();

        //Fuente del encabezado
        Font fonHeader = FontFactory.getFont(FontFactory.TIMES_BOLD);
        fonHeader.setColor(Color.BLACK);
        fonHeader.setSize(18);

        //Creación del titulo
        Paragraph paragraphTitulo = new Paragraph("LISTADO DE MATRICULAS PEDIENTES DE ACTIVAR",fonHeader);
        paragraphTitulo.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(paragraphTitulo);

        //Encabezado
        PdfPTable tableHeader = new PdfPTable(2);
        tableHeader.setSpacingBefore(25);
        tableHeader.setWidthPercentage(105);

        PdfPTable tableHeader2 = new PdfPTable(2);
        tableHeader2.setWidthPercentage(105);

        PdfPTable tableHeader3 = new PdfPTable(2);
        tableHeader3.setWidthPercentage(105);

        PdfPTable tableHeader4 = new PdfPTable(2);
        tableHeader4.setWidthPercentage(105);

        PdfPTable tableHeader5 = new PdfPTable(11);
        tableHeader5.setWidths(new float[]{2f,5f,2f,4.5f,5f,3f,6f,6f,6f,6.3f,6f});
        tableHeader5.setWidthPercentage(105);

        PdfPTable table =  new PdfPTable(1);
        table.setWidthPercentage(105);

        //Instancias de celdas para el encabezdo
        PdfPCell celda = new PdfPCell();
        PdfPCell celda2 = new PdfPCell();
        PdfPCell celda3 = new PdfPCell();
        PdfPCell celda4 = new PdfPCell();
        PdfPCell cell = new PdfPCell();

        //Celdas del cuerpo
        PdfPCell mainTableHeader = new PdfPCell();

        celda.setBackgroundColor(Color.white);
        celda.setPadding(5);
        celda.setHorizontalAlignment(Element.ALIGN_RIGHT);

        celda2.setBackgroundColor(Color.WHITE);
        celda2.setPadding(5);
        celda2.setHorizontalAlignment(Element.ALIGN_RIGHT);

        celda3.setBackgroundColor(Color.white);
        celda3.setPadding(5);
        celda3.setHorizontalAlignment(Element.ALIGN_RIGHT);

        celda4.setBackgroundColor(Color.WHITE);
        celda4.setPadding(5);
        celda4.setHorizontalAlignment(Element.ALIGN_RIGHT);

        cell.setBackgroundColor(Color.GRAY);
        cell.setPadding(10);
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);

        mainTableHeader.setBackgroundColor(Color.red);
        mainTableHeader.setPadding(3);
        mainTableHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
        mainTableHeader.setVerticalAlignment(Element.ALIGN_CENTER);

        //Fuente
        Font fuente = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fuente.setColor(Color.black);
        fuente.setSize(12);

        //Agregando texto a las celdas
        celda.setPhrase(new Phrase("NOMBRE: ",fuente));
        celda2.setPhrase(new Phrase("USUARIO: ",fuente));
        celda3.setPhrase(new Phrase("FECHA: ",fuente));
        celda4.setPhrase(new Phrase("ACTIVIDAD: ",fuente));
        cell.setPhrase(new Phrase("",fuente));
        tableHeader.addCell(celda);
        table.addCell(cell);
        tableHeader2.addCell(celda2);
        tableHeader3.addCell(celda3);
        tableHeader4.addCell(celda4);

            for(QualityControllerLogDto qualityControllerLogDto : qualityControllerLogDtos){
                tableHeader.addCell(" " + qualityControllerLogDto.getQualityControllerLogName().toUpperCase());
                tableHeader2.addCell(" " + qualityControllerLogDto.getUserNameId().toUpperCase());
                tableHeader3.addCell(" " +  new Date().toString().toUpperCase());
                tableHeader4.addCell(" " + qualityControllerLogDto.getActivityId().toUpperCase());
                break;
            }


        mainTableHeader.setPhrase(new Phrase("NO.",fuente));
        tableHeader5.addCell(mainTableHeader);

        mainTableHeader.setPhrase(new Phrase("MATRICULA",fuente));
        tableHeader5.addCell(mainTableHeader);

        mainTableHeader.setPhrase(new Phrase("IDX",fuente));
        tableHeader5.addCell(mainTableHeader);

        mainTableHeader.setPhrase(new Phrase("ESTATUS",fuente));
        tableHeader5.addCell(mainTableHeader);

        mainTableHeader.setPhrase(new Phrase("TIPO LIBRO",fuente));
        tableHeader5.addCell(mainTableHeader);

        mainTableHeader.setPhrase(new Phrase("TOMO",fuente));
        tableHeader5.addCell(mainTableHeader);

        mainTableHeader.setPhrase(new Phrase("INSCRIPCIÓN",fuente));
        tableHeader5.addCell(mainTableHeader);

        mainTableHeader.setPhrase(new Phrase("TRACTO SUCESIVO",fuente));
        tableHeader5.addCell(mainTableHeader);

        mainTableHeader.setPhrase(new Phrase("PROPIEDAD HORIZONTAL",fuente));
        tableHeader5.addCell(mainTableHeader);

        mainTableHeader.setPhrase(new Phrase("OBSERVACIÓN",fuente));
        tableHeader5.addCell(mainTableHeader);

        mainTableHeader.setPhrase(new Phrase("REPORTE DE INCIDENCIA",fuente));
        tableHeader5.addCell(mainTableHeader);

        int i = 0;
        for(QualityControllerLogDto qualityControllerLogDto : qualityControllerLogDtos){
            tableHeader5.addCell(String.valueOf(i+1));
            tableHeader5.addCell(qualityControllerLogDto.getRegistrationNumber());
            tableHeader5.addCell(qualityControllerLogDto.getIndex());
            tableHeader5.addCell(qualityControllerLogDto.getStatusQualityControllerLog().name());
            tableHeader5.addCell(qualityControllerLogDto.getTypeOfBookId());
            tableHeader5.addCell(String.valueOf(qualityControllerLogDto.getTomo()));
            tableHeader5.addCell(String.valueOf(qualityControllerLogDto.getInscription()));
            tableHeader5.addCell(qualityControllerLogDto.getTractId());
            tableHeader5.addCell(qualityControllerLogDto.getHorizontalId());
            tableHeader5.addCell(qualityControllerLogDto.getObservation());
            tableHeader5.addCell(qualityControllerLogDto.getIncidentReport());
            i++;
        }

        document.add(tableHeader);
        document.add(tableHeader2);
        document.add(tableHeader3);
        document.add(tableHeader4);
        document.add(table);
        document.add(tableHeader5);
        document.close();
    }

}
