package org.itechciv.dashboard.helper;

public class AgeRange {

    private String label;
    private Integer deletionTotal;
    private Integer notDeletionTotal;
    private double deletionPercentage;

    public AgeRange() {
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getDeletionTotal() {
        return deletionTotal;
    }

    public void setDeletionTotal(Integer deletionTotal) {
        this.deletionTotal = deletionTotal;
    }

    public Integer getNotDeletionTotal() {
        return notDeletionTotal;
    }

    public void setNotDeletionTotal(Integer notDeletionTotal) {
        this.notDeletionTotal = notDeletionTotal;
    }

    public double getDeletionPercentage() {
        return deletionPercentage;
    }

    public void setDeletionPercentage(double deletionPercentage) {
        this.deletionPercentage = deletionPercentage;
    } 

    

    



}
