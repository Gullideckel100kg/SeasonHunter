package gullideckel.seasonhunter.Interfaces;

import java.util.List;

import gullideckel.seasonhunter.Objects.Job.CompanyDocument;

public interface IFilteredDocuments
{
    void RecieveFilterdDocuments(List<CompanyDocument> docs);
}
