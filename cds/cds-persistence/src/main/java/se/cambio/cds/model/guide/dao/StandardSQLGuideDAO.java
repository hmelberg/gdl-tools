package se.cambio.cds.model.guide.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import se.cambio.cds.model.guide.dto.GuideDTO;
import se.cambio.cds.util.exceptions.GuideNotFoundException;
import se.cambio.openehr.model.util.conversors.DBConversion;
import se.cambio.openehr.model.util.sql.GeneralOperations;
import se.cambio.openehr.util.exceptions.InternalErrorException;

/**
 * @author iago.corbal
 *
 */
public class StandardSQLGuideDAO implements SQLGuideDAO {

    public GuideDTO searchByGuideId(Connection connection, String guideId) 
	    throws InternalErrorException, GuideNotFoundException {
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	try {
	    /* Create "preparedStatement". */
	    String queryString = "SELECT guideSrc, guideObject, compiledGuide, active FROM cds_guide WHERE guideid = ?";
	    preparedStatement = connection.prepareStatement(queryString);

	    /* Fill "preparedStatement". */
	    int i = 1;
	    preparedStatement.setString(i++, guideId);

	    /* Execute query. */
	    resultSet = preparedStatement.executeQuery();

	    if (!resultSet.next()) {
		throw new GuideNotFoundException(guideId);
	    }

	    /* Get results. */
	    i = 1;
	    String guideSrc = resultSet.getString(i++);
	    byte[] guide = resultSet.getBytes(i++);
	    byte[] compiledGuide = resultSet.getBytes(i++);
	    boolean active = DBConversion.toBoolean(resultSet.getShort(i++));
	    /* Return the value object. */
	    return new GuideDTO(guideId, guideSrc, guide, compiledGuide, active);
	} catch (SQLException e) {
	    throw new InternalErrorException(e);
	} finally {
	    GeneralOperations.closeResultSet(resultSet);
	    GeneralOperations.closeStatement(preparedStatement);
	}
    }

    public Collection<GuideDTO> searchAll(Connection connection) 
	    throws InternalErrorException {
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	try {

	    /* Create "preparedStatement". */
	    String queryString = 
		    "SELECT guideid, guideSrc, guideObject, compiledGuide, active FROM cds_guide";
	    preparedStatement = connection.prepareStatement(queryString);

	    /* Execute query. */
	    resultSet = preparedStatement.executeQuery();

	    Collection<GuideDTO> guideDTOs = new ArrayList<GuideDTO>();
	    if (!resultSet.next()) {
		return guideDTOs;
	    }
	    do {
		/* Get results. */
		int i = 1;
		String guideId = resultSet.getString(i++);
		String guideSrc = resultSet.getString(i++);
		byte[] guide = resultSet.getBytes(i++);
		byte[] compiledGuide = resultSet.getBytes(i++);
		boolean active = DBConversion.toBoolean(resultSet.getShort(i++));
		GuideDTO guideDTO = 
			new GuideDTO(guideId, guideSrc, guide, compiledGuide, active);
		guideDTOs.add(guideDTO);
	    } while (resultSet.next());

	    /* Return value objects. */
	    return guideDTOs;
	} catch (SQLException e) {
	    throw new InternalErrorException(e);
	} finally {
	    GeneralOperations.closeResultSet(resultSet);
	    GeneralOperations.closeStatement(preparedStatement);
	}
    }

    public GuideDTO insert(Connection connection, GuideDTO guideDTO) 
	    throws InternalErrorException {
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	try {
	    /* Create "preparedStatement". */
	    String queryString = "INSERT INTO cds_guide (guideid, guideSrc, guideObject, compiledGuide, active) VALUES (?, ?, ?, ?, ?)";
	    preparedStatement = connection.prepareStatement(queryString);

	    /* Fill "preparedStatement". */
	    int i = 1;
	    preparedStatement.setString(i++, guideDTO.getIdGuide());
	    preparedStatement.setString(i++, guideDTO.getGuideSrc());
	    preparedStatement.setBytes(i++, guideDTO.getGuideObject());
	    preparedStatement.setBytes(i++, guideDTO.getCompiledGuide());
	    preparedStatement.setShort(i++, DBConversion.toShort(guideDTO.isActive()));
	    
	    /* Execute query. */
	    int insertedRows = preparedStatement.executeUpdate();

	    if (insertedRows == 0) {
		throw new SQLException("Can not add row to table 'cds_guide'");
	    }

	    if (insertedRows > 1) {
		throw new SQLException("Duplicate row in table 'cds_guide'");
	    }
	    return guideDTO;
	} catch (SQLException e) {
	    throw new InternalErrorException(e);
	} finally {
	    GeneralOperations.closeResultSet(resultSet);
	    GeneralOperations.closeStatement(preparedStatement);
	}
    }

    public void update(Connection connection, GuideDTO guideDTO) 
	    throws InternalErrorException, GuideNotFoundException {
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	try {
	    /* Create "preparedStatement". */
	    String queryString = "UPDATE cds_guide SET guideSrc = ?, guideObject = ?, compiledGuide = ?, active = ? WHERE guideid = ?";
	    preparedStatement = connection.prepareStatement(queryString);

	    /* Fill "preparedStatement". */
	    int i = 1;
	    preparedStatement.setString(i++, guideDTO.getGuideSrc());
	    preparedStatement.setBytes(i++, guideDTO.getGuideObject());
	    preparedStatement.setBytes(i++, guideDTO.getCompiledGuide());
	    preparedStatement.setShort(i++, DBConversion.toShort(guideDTO.isActive()));
	    preparedStatement.setString(i++, guideDTO.getIdGuide());

	    /* Execute query. */
	    int updatedRows = preparedStatement.executeUpdate();

	    if (updatedRows == 0) {
		throw new GuideNotFoundException(
			guideDTO.getIdGuide());
	    }

	    if (updatedRows > 1) {
		throw new SQLException("Duplicate row in table 'cds_guide'");
	    }
	} catch (SQLException e) {
	    throw new InternalErrorException(e);
	} finally {
	    GeneralOperations.closeResultSet(resultSet);
	    GeneralOperations.closeStatement(preparedStatement);
	}
    }

    public void remove(Connection connection, String guideId) 
	    throws InternalErrorException, GuideNotFoundException {
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	try {
	    /* Create "preparedStatement". */
	    String queryString = "DELETE FROM cds_guide WHERE guideid = ?";
	    preparedStatement = connection.prepareStatement(queryString);

	    /* Fill "preparedStatement". */
	    int i = 1;
	    preparedStatement.setString(i++, guideId);

	    /* Execute query. */
	    int deletedRows = preparedStatement.executeUpdate();
	    if (deletedRows == 0) {
		throw new GuideNotFoundException(guideId);
	    }
	} catch (SQLException e) {
	    throw new InternalErrorException(e);
	} finally {
	    GeneralOperations.closeResultSet(resultSet);
	    GeneralOperations.closeStatement(preparedStatement);
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