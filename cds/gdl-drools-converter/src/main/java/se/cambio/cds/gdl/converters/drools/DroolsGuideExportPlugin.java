package se.cambio.cds.gdl.converters.drools;

import se.cambio.cds.controller.guide.GuideExportPlugin;
import se.cambio.cds.gdl.model.Guide;
import se.cambio.cds.util.exceptions.GuideCompilationException;
import se.cambio.openehr.controller.session.data.ArchetypeManager;
import se.cambio.openehr.util.exceptions.InternalErrorException;

public class DroolsGuideExportPlugin implements GuideExportPlugin {

    public DroolsGuideExportPlugin(){
    }


    @Override
    public String getPluginName() {
        return "Drools";
    }

    @Override
    public byte[] compile(Guide guide) throws InternalErrorException{
        try {
            return CompilationManager.compile(getDroolsGuide(guide));
        } catch (CompilationErrorException e) {
            throw new InternalErrorException(e);
        }
    }

    public static String getDroolsGuide(Guide guide) throws InternalErrorException {
        try{
            return new GDLDroolsConverter(guide, ArchetypeManager.getInstance()).convertToDrools();
        }catch (InternalErrorException e) {
            throw new GuideCompilationException(guide.getId(), e);
        }catch (Throwable th) {
            throw new GuideCompilationException(guide.getId(), new Exception(th)); //TODO
        }
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