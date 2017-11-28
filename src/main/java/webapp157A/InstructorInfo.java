package webapp157A;

public class InstructorInfo {

    private String userId;
    private String biography;
    private String photo;
    private String position;

    private Department departmentTeachesFor;
    private OfficeHours officeHours;

    // Getters and setters:

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getBiography() { return biography; }
    public void setBiography(String biography) { this.biography = biography; }

    public String getPhoto() { return photo; }
    public void setPhoto(String photo) { this.photo = photo; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    public Department getDepartmentTeachesFor() { return departmentTeachesFor; }
    public void setDepartmentTeachesFor(Department departmentTeachesFor) { this.departmentTeachesFor = departmentTeachesFor; }

    public OfficeHours getOfficeHours() { return officeHours; }
    public void setOfficeHours(OfficeHours officeHours) { this.officeHours = officeHours; }

    // Other methods:

    /*
        Replaces fields (except for userId) of this instructor info with fields in newInstructorInfo if
         the new fields are not empty strings.
     */
    public void update(InstructorInfo newInstructorInfo)
    {
        if (newInstructorInfo.biography != null && !newInstructorInfo.biography.equals("")) {
            setBiography(newInstructorInfo.getBiography());
        }

        if (newInstructorInfo.photo != null && !newInstructorInfo.photo.equals("")) {
            setPhoto(newInstructorInfo.getPhoto());
        }

        if (newInstructorInfo.position != null && !newInstructorInfo.position.equals("")) {
            setPosition(newInstructorInfo.getPosition());
        }
    }

    // General:

    @Override
    public String toString()
    {
        return "<instructor> "
                + "userId="  + userId + ", "
                + "biography="  + biography + ", "
                + "photo="  + photo + ", "
                + "position="  + position
                + "</instructor>";
    }
}
