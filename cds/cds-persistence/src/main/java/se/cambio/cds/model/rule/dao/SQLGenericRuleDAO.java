package se.cambio.cds.model.rule.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

import javax.sql.DataSource;

import se.cambio.cds.model.rule.dto.RuleDTO;
import se.cambio.cds.model.util.GlobalNames;
import se.cambio.cds.model.util.sql.DataSourceLocator;
import se.cambio.cds.model.util.sql.GeneralOperations;
import se.cambio.cds.util.exceptions.InternalErrorException;
import se.cambio.cds.util.exceptions.ModelException;

/**
 * @author iago.corbal
 */
public class SQLGenericRuleDAO implements GenericRuleDAO {


    private SQLRuleDAO dao;
    private DataSource dataSource;

    public SQLGenericRuleDAO() throws InternalErrorException {
	dao = SQLRuleFactory.getDAO();
	dataSource = DataSourceLocator.getDataSource(GlobalNames.CDSS_DATA_SOURCE);
    }
    
    public RuleDTO search(String idRule)
	    throws InternalErrorException, ModelException {
	Connection conexion = null;
	try {
	    conexion = dataSource.getConnection();
	    return dao.search(conexion, idRule);
	} catch (SQLException e) {
	    throw new InternalErrorException(e);
	} finally {
	    GeneralOperations.closeConnection(conexion);
	}
    }

    public Collection<String> searchAllIds()
    throws InternalErrorException, ModelException{
	Connection conexion = null;
	try {
	    conexion = dataSource.getConnection();
	    return dao.searchAllIds(conexion);
	} catch (SQLException e) {
	    throw new InternalErrorException(e);
	} finally {
	    GeneralOperations.closeConnection(conexion);
	}
    }

    public void add(RuleDTO ruleDTO)
	    throws InternalErrorException, ModelException {
	Connection conexion = null;
	try {
	    conexion = dataSource.getConnection();
	    dao.add(conexion,ruleDTO);
	} catch (SQLException e) {
	    throw new InternalErrorException(e);
	} finally {
	    GeneralOperations.closeConnection(conexion);
	}
    }
    
    public void remove(String idRule)
	    throws InternalErrorException, ModelException {
	Connection conexion = null;
	try {
	    conexion = dataSource.getConnection();
	    dao.remove(conexion,idRule);
	} catch (SQLException e) {
	    throw new InternalErrorException(e);
	} finally {
	    GeneralOperations.closeConnection(conexion);
	}
    }
    
    public void removeByGuideId(Integer idGuide)
	    throws InternalErrorException, ModelException {
	Connection conexion = null;
	try {
	    conexion = dataSource.getConnection();
	    dao.removeByGuideId(conexion,idGuide);
	} catch (SQLException e) {
	    throw new InternalErrorException(e);
	} finally {
	    GeneralOperations.closeConnection(conexion);
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