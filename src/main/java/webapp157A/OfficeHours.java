package webapp157A;

import java.sql.Time;

public class OfficeHours {

    private String userId;
    private java.sql.Time startTime;
    private java.sql.Time endTime;
    private String daysOfTheWeek;

    private int roomNumber;
    private String buildingName;

    // Getters and Setters:


    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public Time getStartTime() { return startTime; }
    public void setStartTime(Time startTime) { this.startTime = startTime; }

    public Time getEndTime() { return endTime; }
    public void setEndTime(Time endTime) { this.endTime = endTime; }

    public String getDaysOfTheWeek() { return daysOfTheWeek; }
    public void setDaysOfTheWeek(String daysOfTheWeek) { this.daysOfTheWeek = daysOfTheWeek; }

    public int getRoomNumber() { return roomNumber; }
    public void setRoomNumber(int roomNumber) { this.roomNumber = roomNumber; }

    public String getBuildingName() { return buildingName; }
    public void setBuildingName(String buildingName) { this.buildingName = buildingName; }
}
