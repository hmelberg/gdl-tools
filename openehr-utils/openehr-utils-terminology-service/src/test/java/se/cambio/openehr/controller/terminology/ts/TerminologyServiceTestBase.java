package se.cambio.openehr.controller.terminology.ts;

import junit.framework.TestCase;
import org.openehr.rm.datatypes.text.CodePhrase;
import se.cambio.openehr.controller.terminology.TerminologyServiceImpl;
import se.cambio.openehr.util.UserConfigurationManager;

import java.net.URISyntaxException;

public class TerminologyServiceTestBase extends TestCase {

    protected TerminologyServiceImpl ts;
    protected static final CodePhrase EN = new CodePhrase("ISO_639-1", "en");
    protected static final CodePhrase SV = new CodePhrase("ISO_639-1", "sv");
    protected static final String SCT = "SNOMED-CT";
    protected static final String ICD10 = "ICD10";

    public TerminologyServiceTestBase() throws URISyntaxException {
        UserConfigurationManager.setParameter(UserConfigurationManager.TERMINOLOGIES_FOLDER_KW, TerminologyServiceTestBase.class.getClassLoader().getResource("terminologies1").toURI().getPath());
        ts = new TerminologyServiceImpl();
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