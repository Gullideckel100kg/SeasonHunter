package gullideckel.seasonhunter.Interfaces;

import java.util.List;

import gullideckel.seasonhunter.Objects.JobInformation.CompanyDocument;

public interface IDocumentList
{
    void recievedDocuments(List<CompanyDocument> docs);
}
