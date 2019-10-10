package com.siti.wisdomhydrologic.maintainconfig.entity;

public class ConfigAbnormalError {



  private String errorId;
  private String errorName;
  private Integer belongWhichTable;
  private String table1Relate;
  private String description;

  public String getErrorId() {
    return errorId;
  }

  public void setErrorId(String errorId) {
    this.errorId = errorId;
  }

  public String getErrorName() {
    return errorName;
  }

  public void setErrorName(String errorName) {
    this.errorName = errorName;
  }

  public Integer getBelongWhichTable() {
    return belongWhichTable;
  }

  public void setBelongWhichTable(Integer belongWhichTable) {
    this.belongWhichTable = belongWhichTable;
  }

  public String getTable1Relate() {
    return table1Relate;
  }

  public void setTable1Relate(String table1Relate) {
    this.table1Relate = table1Relate;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return "ConfigAbnormalError{" + "errorId='" + errorId + '\'' + ", errorName='" + errorName + '\'' + ", belongWhichTable=" + belongWhichTable + ", table1Relate='" + table1Relate + '\'' + ", description='" + description + '\'' + '}';
  }

  public ConfigAbnormalError() {
  }
}
