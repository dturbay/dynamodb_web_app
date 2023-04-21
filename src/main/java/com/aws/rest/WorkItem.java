/*
   Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
   SPDX-License-Identifier: Apache-2.0
*/

package com.aws.rest;

public class WorkItem {

  private String id;
  private String name;
  private String guide;
  private String date;
  private String description;
  private String status;
  private int archived;

  public int getArchived() {
    return this.archived;
  }

  public void setArchived(int archived) {
    this.archived = archived;
  }

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getStatus() {
    return this.status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getDate() {
    return this.date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getGuide() {
    return this.guide;
  }

  public void setGuide(String guide) {
    this.guide = guide;
  }
}
