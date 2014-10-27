package se.cambio.cm.model.template.dto;

import se.cambio.cm.model.util.CMElement;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="cm_template")
public class TemplateDTO implements CMElement{
    @Id
    private java.lang.String id;
    private java.lang.String arcehtypeId;
    @Column(columnDefinition="TEXT")
    private java.lang.String source;
    private byte[] aom;
    private byte[] aobcVO;
    private Date lastUpdate;

    private static final long serialVersionUID = 23032012L;

    public TemplateDTO() {
    }

    public TemplateDTO(String id, String arcehtypeId, String source, byte[] aom, byte[] aobcVO, Date lastUpdate) {
        this.id = id;
        this.arcehtypeId = arcehtypeId;
        this.source = source;
        this.aom = aom;
        this.aobcVO = aobcVO;
        this.lastUpdate = lastUpdate;
    }

    public java.lang.String getArcehtypeId() {
        return arcehtypeId;
    }

    public void setArcehtypeId(java.lang.String arcehtypeId) {
        this.arcehtypeId = arcehtypeId;
    }

    public byte[] getAom() {
        return aom;
    }

    public void setAom(byte[] aom) {
        this.aom = aom;
    }

    public byte[] getAobcVO() {
        return aobcVO;
    }

    public void setAobcVO(byte[] aobcVO) {
        this.aobcVO = aobcVO;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getSource() {
        return source;
    }

    @Override
    public Date getLastUpdate() {
        return lastUpdate;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public void setLastUpdate(Date lastUpdate) {
         this.lastUpdate = lastUpdate;
    }
}
/*
 *  ***** BEGIN LICENSE BLOCK *****
 *  Version: MPL 2.0/GPL 2.0/LGPL 2.1
 *
 *  The contents of this file are subject to the Mozilla Public License Version
 *  2.0 (the 'License'); you may not use this file except in compliance with
 *  the License. You may obtain a copy of the License at
 *  http://www.mozilla.org/MPL/
 *
 *  Software distributed under the License is distributed on an 'AS IS' basis,
 *  WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 *  for the specific language governing rights and limitations under the
 *  License.
 *
 *
 *  The Initial Developers of the Original Code are Iago Corbal and Rong Chen.
 *  Portions created by the Initial Developer are Copyright (C) 2012-2013
 *  the Initial Developer. All Rights Reserved.
 *
 *  Contributor(s):
 *
 * Software distributed under the License is distributed on an 'AS IS' basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 *  ***** END LICENSE BLOCK *****
 */