package com.hs.mobile.gw.openapi.vo;

public class DeptVO {
	private String deptID;
	private String fldrID;
    private String name;
    private String fullName;

    public DeptVO(String name, String fullName, String deptID, String fldrID ) {
        this.deptID = deptID;
        this.fldrID = fldrID;
        this.name = name;
        this.setFullName(fullName);
    }

    public String getDeptID() {
		return deptID;
	}

	public void setDeptID(String deptID) {
		this.deptID = deptID;
	}

	public String getFldrID() {
		return fldrID;
	}

	public void setFldrID(String fldrID) {
		this.fldrID = fldrID;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}    

    //to display object as a string in spinner
    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof DeptVO){
        	DeptVO c = (DeptVO )obj;
            if(c.getName().equals(name) && c.getDeptID()== deptID && c.getFldrID()== fldrID ) return true;
        }

        return false;
    }

}
