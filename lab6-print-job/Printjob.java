/**
 * Represents a single document to be printed.
 * Stores the document name and page count.
 */
class PrintJob {
    private String documentName;
    private int pageCount;

    /**
     * Constructs a new PrintJob with the specified document name and page count.
     * @param documentName The name of the document to print
     * @param pageCount The number of pages in the document
     */
    public PrintJob(String documentName, int pageCount) {
        this.documentName = documentName;
        this.pageCount = pageCount;
    }

    /**
     * Gets the document name.
     * @return the document name
     */
    public String getDocumentName() {
        return documentName;
    }

    /**
     * Gets the page count.
     * @return the number of pages
     */
    public int getPageCount() {
        return pageCount;
    }

    /**
     * Returns a descriptive string representation of the print job.
     * @return formatted string with document name and page count
     */
    @Override
    public String toString() {
        return "PrintJob[Document: " + documentName + ", Pages: " + pageCount + "]";
    }
}
