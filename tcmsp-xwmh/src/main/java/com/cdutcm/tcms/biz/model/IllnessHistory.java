package com.cdutcm.tcms.biz.model;

public class IllnessHistory {


	private Long id;
    /** 主诉 */
    private String chiefComplaint;
    /** 现病史 */
    private String presentIllness;
    /** 既往史 */
    private String passedIllness;
    /** 个人疾病史 */
    private String personalIllness;
    /** 婚姻史 */
    private String marriageHistory;
    /** 过敏史 */
    private String allergicHistory;
    /** 家庭史 */
    private String familyHistory;
    /** 月经史 */
	private String menstruationHistory;
	/**
	 * 体征
	 * @return
	 */
    private String tz;
    /**
     * 舌象
     * @return
     */
    private String tongue;
    /**
     * 脉象
     * @return
     */
    private String moss;
    /**
     * 苔象:苔质
     * @return
     */
    private String pulse;
    /**
     * 苔象:苔色
     * @return
     */
    private String pulcolor;
    
    public String getPulcolor() {
		return pulcolor;
	}

	public void setPulcolor(String pulcolor) {
		this.pulcolor = pulcolor;
	}

	public String getTz() {
		return tz;
	}

	public void setTz(String tz) {
		this.tz = tz;
	}

	public String getTongue() {
		return tongue;
	}

	public void setTongue(String tongue) {
		this.tongue = tongue;
	}

	public String getMoss() {
		return moss;
	}

	public void setMoss(String moss) {
		this.moss = moss;
	}

	public String getPulse() {
		return pulse;
	}

	public void setPulse(String pulse) {
		this.pulse = pulse;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChiefComplaint() {
        return chiefComplaint;
    }

    public void setChiefComplaint(String chiefComplaint) {
        this.chiefComplaint = chiefComplaint == null ? null : chiefComplaint.trim();
    }

    public String getPresentIllness() {
        return presentIllness;
    }

    public void setPresentIllness(String presentIllness) {
        this.presentIllness = presentIllness == null ? null : presentIllness.trim();
    }

    public String getPassedIllness() {
        return passedIllness;
    }

    public void setPassedIllness(String passedIllness) {
        this.passedIllness = passedIllness == null ? null : passedIllness.trim();
    }

    public String getPersonalIllness() {
        return personalIllness;
    }

    public void setPersonalIllness(String personalIllness) {
        this.personalIllness = personalIllness == null ? null : personalIllness.trim();
    }

    public String getMarriageHistory() {
        return marriageHistory;
    }

    public void setMarriageHistory(String marriageHistory) {
        this.marriageHistory = marriageHistory == null ? null : marriageHistory.trim();
    }

    public String getAllergicHistory() {
        return allergicHistory;
    }

    public void setAllergicHistory(String allergicHistory) {
        this.allergicHistory = allergicHistory == null ? null : allergicHistory.trim();
    }

    public String getFamilyHistory() {
        return familyHistory;
    }

    public void setFamilyHistory(String familyHistory) {
        this.familyHistory = familyHistory == null ? null : familyHistory.trim();
    }

	public String getMenstruationHistory() {
		return menstruationHistory;
	}

	public void setMenstruationHistory(String menstruationHistory) {
		this.menstruationHistory = menstruationHistory;
	}
	
    @Override
	public String toString() {
		return "IllnessHistory [id=" + id + ", chiefComplaint="
				+ chiefComplaint + ", presentIllness=" + presentIllness
				+ ", passedIllness=" + passedIllness + ", personalIllness="
				+ personalIllness + ", marriageHistory=" + marriageHistory
				+ ", allergicHistory=" + allergicHistory + ", familyHistory="
				+ familyHistory + ", menstruationHistory="
				+ menstruationHistory + ", tz=" + tz + ", tongue=" + tongue
				+ ", moss=" + moss + ", pulse=" + pulse + "]";
	}
}