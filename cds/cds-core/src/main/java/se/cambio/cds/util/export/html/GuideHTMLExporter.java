package se.cambio.cds.util.export.html;

import se.cambio.cds.gdl.model.Guide;
import se.cambio.cds.gdl.model.readable.ReadableGuide;
import se.cambio.cds.gdl.model.readable.rule.ReadableRule;
import se.cambio.cds.util.GuideImporter;
import se.cambio.openehr.controller.session.data.ArchetypeManager;
import se.cambio.openehr.util.exceptions.InternalErrorException;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class GuideHTMLExporter extends ClinicalModelHTMLExporter<Guide> {

    public GuideHTMLExporter(ArchetypeManager archetypeManager) {
        super(archetypeManager);
    }

    @Override
    public Map<String, Object> getEntityObjectsMap() throws InternalErrorException {
        String lang = getLanguage();
        ReadableGuide readableGuide = GuideImporter.importGuide(getEntity(), lang, getArchetypeManager());
        Collection<String> htmlReadableRules = getHTMLReadableRules(readableGuide, lang);
        Map<String, Object> objectMap = new HashMap<String, Object>();
        objectMap.put("guide", getEntity());
        objectMap.put("guide_details", getEntity().getDescription().getDetails().get(lang));
        objectMap.put("guide_definitions", readableGuide);
        objectMap.put("guide_preconditions", readableGuide.getPreconditionRuleLines().getRuleLines());
        objectMap.put("guide_rules", htmlReadableRules);
        objectMap.put("guide_terms", getEntity().getOntology().getTermDefinitions().get(lang).getTerms());
        return objectMap;
    }

    private static Collection<String> getHTMLReadableRules(ReadableGuide readableGuide, String lang){
        Collection<String> htmlReadableRules = new ArrayList<String>();
        for(ReadableRule readableRule: readableGuide.getReadableRules().values()){
            htmlReadableRules.add(readableRule.toHTMLString(lang));
        }
        return htmlReadableRules;
    }


    @Override
    public Map<String, String> getEntityTextMap() {
        HashMap<String, String> textsMap = new HashMap<String, String>();
        addText(textsMap, "GuideDetails");
        addText(textsMap, "Preconditions");
        addText(textsMap, "RuleList");
        addText(textsMap, "Bindings");
        return textsMap;
    }

    @Override
    public InputStream getInputStreamTemplate() {
        return GuideHTMLExporter.class.getClassLoader().getResourceAsStream("gdl.ftl");
    }
}
