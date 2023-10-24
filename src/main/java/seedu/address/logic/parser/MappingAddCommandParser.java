package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PARAMETER_INFORMATION;
import static seedu.address.logic.parser.CliSyntax.PARAMETER_LOCALCODE;
import static seedu.address.logic.parser.CliSyntax.PARAMETER_PARTNERCODE;
import static seedu.address.logic.parser.CliSyntax.PARAMETER_UNIVERSITY;
import static seedu.address.logic.parser.ParserUtil.areValuesEnclosedAndNonEmpty;

import seedu.address.logic.commands.MappingAddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.localcourse.LocalCode;
import seedu.address.model.mapping.Mapping;
import seedu.address.model.mapping.MappingMiscInformation;
import seedu.address.model.partnercourse.PartnerCode;
import seedu.address.model.university.UniversityName;

/**
 * Parses input arguments and creates a new Mapping object.
 */
public class MappingAddCommandParser implements Parser<MappingAddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the MappingAddCommand
     * and returns an MappingAddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public MappingAddCommand parse(String args) throws ParseException {
        if (!areValuesEnclosedAndNonEmpty(args)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                            MappingAddCommand.MAPPING_ADD_MESSAGE_USAGE));
        }

        SeplendidArgumentMap parameterToArgMap =
                SeplendidArgumentTokenizer.tokenize(args, PARAMETER_LOCALCODE, PARAMETER_UNIVERSITY,
                        PARAMETER_PARTNERCODE, PARAMETER_INFORMATION);

        if (!ParserUtil.areArgumentsPresent(parameterToArgMap, PARAMETER_LOCALCODE, PARAMETER_UNIVERSITY,
                PARAMETER_PARTNERCODE, PARAMETER_INFORMATION)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    MappingAddCommand.MAPPING_ADD_MESSAGE_USAGE));
        }

        // All arguments should be a non-empty {@code Optional}
        LocalCode localCode = ParserUtil.parseLocalCode(parameterToArgMap.getValue(PARAMETER_LOCALCODE).get());
        UniversityName universityName = ParserUtil.parseUniversityName(
                parameterToArgMap.getValue(PARAMETER_UNIVERSITY).get());
        PartnerCode partnerCode = ParserUtil.parsePartnerCode(parameterToArgMap.getValue(PARAMETER_PARTNERCODE).get());
        MappingMiscInformation mappingMiscInformation = ParserUtil.parseMappingMiscInformation(
                parameterToArgMap.getValue(PARAMETER_INFORMATION).get());

        Mapping mapping = new Mapping(localCode, universityName, partnerCode, mappingMiscInformation);

        return new MappingAddCommand(mapping);
    }

}
