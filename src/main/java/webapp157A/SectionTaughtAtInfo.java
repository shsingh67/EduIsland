package webapp157A;

import java.sql.Time;

public class SectionTaughtAtInfo {

    private String sectionId;
    private int roomNumber;
    private String buildingName;
    private Time startTime;
    private Time endTime;
    private String daysOfWeek;

    // Getters and Setters:

    public String getSectionId() { return sectionId; }
    public void setSectionId(String sectionId) { this.sectionId = sectionId; }

    public int getRoomNumber() { return roomNumber; }
    public void setRoomNumber(int roomNumber) { this.roomNumber = roomNumber; }

    public String getBuildingName() { return buildingName; }
    public void setBuildingName(String buildingName) { this.buildingName = buildingName; }

    public Time getStartTime() { return startTime; }
    public void setStartTime(Time startTime) { this.startTime = startTime; }

    public Time getEndTime() { return endTime; }
    public void setEndTime(Time endTime) { this.endTime = endTime; }

    public String getDaysOfWeek() { return daysOfWeek; }
    public void setDaysOfWeek(String daysOfWeek) { this.daysOfWeek = daysOfWeek; }
}
