package se.cambio.cm.model.archetype.vo;


/**
 * @author iago.corbal
 *
 */
public class ArchetypeElementVO extends PathableVO {

	private static final long serialVersionUID = 23032012L;

	public ArchetypeElementVO(
		String name, String description, String type,
		String idArchetype, String idTemplate, String path) {
	    super(name, description, type,  idArchetype, idTemplate, path);
	}

    @Override
    public ArchetypeElementVO clone(){
        return new ArchetypeElementVOBuilder()
                .setName(getName())
                .setDescription(getDescription())
                .setType(getType())
                .setIdArchetype(getIdArchetype())
                .setIdTemplate(getIdTemplate())
                .setPath(getPath())
                .createArchetypeElementVO();
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