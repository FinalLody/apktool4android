// $ANTLR 3.2 Sep 23, 2009 14:05:07 org/jf/smali/smaliTreeWalker.g 2011-02-10 14:15:53

package org.jf.smali;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.regex.*;
import java.lang.Float;
import java.lang.Double;

import org.jf.dexlib.*;
import org.jf.dexlib.EncodedValue.*;
import org.jf.dexlib.Util.*;
import org.jf.dexlib.Code.*;
import org.jf.dexlib.Code.Format.*;


import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class smaliTreeWalker extends TreeParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "CLASS_DIRECTIVE", "SUPER_DIRECTIVE", "IMPLEMENTS_DIRECTIVE", "SOURCE_DIRECTIVE", "FIELD_DIRECTIVE", "END_FIELD_DIRECTIVE", "SUBANNOTATION_DIRECTIVE", "END_SUBANNOTATION_DIRECTIVE", "ANNOTATION_DIRECTIVE", "END_ANNOTATION_DIRECTIVE", "ENUM_DIRECTIVE", "METHOD_DIRECTIVE", "END_METHOD_DIRECTIVE", "REGISTERS_DIRECTIVE", "LOCALS_DIRECTIVE", "ARRAY_DATA_DIRECTIVE", "END_ARRAY_DATA_DIRECTIVE", "PACKED_SWITCH_DIRECTIVE", "END_PACKED_SWITCH_DIRECTIVE", "SPARSE_SWITCH_DIRECTIVE", "END_SPARSE_SWITCH_DIRECTIVE", "CATCH_DIRECTIVE", "CATCHALL_DIRECTIVE", "LINE_DIRECTIVE", "PARAMETER_DIRECTIVE", "END_PARAMETER_DIRECTIVE", "LOCAL_DIRECTIVE", "END_LOCAL_DIRECTIVE", "RESTART_LOCAL_DIRECTIVE", "PROLOGUE_DIRECTIVE", "EPILOGUE_DIRECTIVE", "HEX_PREFIX", "HEX_DIGIT", "BASE_INTEGER", "DECIMAL_EXPONENT", "BINARY_EXPONENT", "HEX_DIGITS", "BASE_FLOAT_OR_ID", "BASE_FLOAT", "ESCAPE_SEQUENCE", "POSITIVE_INTEGER_LITERAL", "NEGATIVE_INTEGER_LITERAL", "LONG_LITERAL", "SHORT_LITERAL", "BYTE_LITERAL", "FLOAT_LITERAL_OR_ID", "DOUBLE_LITERAL_OR_ID", "FLOAT_LITERAL", "DOUBLE_LITERAL", "BOOL_LITERAL", "NULL_LITERAL", "BASE_STRING_LITERAL", "STRING_LITERAL", "BASE_CHAR_LITERAL", "CHAR_LITERAL", "REGISTER", "ANNOTATION_VISIBILITY", "ACCESS_SPEC", "VTABLE_OFFSET", "FIELD_OFFSET", "OFFSET", "LINE_COMMENT", "INSTRUCTION_FORMAT10t", "INSTRUCTION_FORMAT10x", "INSTRUCTION_FORMAT11n", "INSTRUCTION_FORMAT11x", "INSTRUCTION_FORMAT12x_OR_ID", "INSTRUCTION_FORMAT12x", "INSTRUCTION_FORMAT20t", "INSTRUCTION_FORMAT21c_FIELD", "INSTRUCTION_FORMAT21c_STRING", "INSTRUCTION_FORMAT21c_TYPE", "INSTRUCTION_FORMAT21h", "INSTRUCTION_FORMAT21s", "INSTRUCTION_FORMAT21t", "INSTRUCTION_FORMAT22b", "INSTRUCTION_FORMAT22c_FIELD", "INSTRUCTION_FORMAT22c_TYPE", "INSTRUCTION_FORMAT22cs_FIELD", "INSTRUCTION_FORMAT22s_OR_ID", "INSTRUCTION_FORMAT22s", "INSTRUCTION_FORMAT22t", "INSTRUCTION_FORMAT22x", "INSTRUCTION_FORMAT23x", "INSTRUCTION_FORMAT30t", "INSTRUCTION_FORMAT31c", "INSTRUCTION_FORMAT31i_OR_ID", "INSTRUCTION_FORMAT31i", "INSTRUCTION_FORMAT31t", "INSTRUCTION_FORMAT32x", "INSTRUCTION_FORMAT35c_METHOD", "INSTRUCTION_FORMAT35c_TYPE", "INSTRUCTION_FORMAT35s_METHOD", "INSTRUCTION_FORMAT35ms_METHOD", "INSTRUCTION_FORMAT3rc_METHOD", "INSTRUCTION_FORMAT3rc_TYPE", "INSTRUCTION_FORMAT3rms_METHOD", "INSTRUCTION_FORMAT51l", "BASE_SIMPLE_NAME", "BASE_PRIMITIVE_TYPE", "BASE_CLASS_DESCRIPTOR", "BASE_ARRAY_DESCRIPTOR", "BASE_TYPE", "PRIMITIVE_TYPE", "VOID_TYPE", "CLASS_DESCRIPTOR", "ARRAY_DESCRIPTOR", "PARAM_LIST_OR_ID", "PARAM_LIST", "SIMPLE_NAME", "METHOD_NAME", "DOTDOT", "ARROW", "EQUAL", "COLON", "COMMA", "OPEN_BRACE", "CLOSE_BRACE", "OPEN_PAREN", "CLOSE_PAREN", "WHITE_SPACE", "I_CLASS_DEF", "I_SUPER", "I_IMPLEMENTS", "I_SOURCE", "I_ACCESS_LIST", "I_METHODS", "I_FIELDS", "I_FIELD", "I_FIELD_TYPE", "I_FIELD_INITIAL_VALUE", "I_METHOD", "I_METHOD_PROTOTYPE", "I_METHOD_RETURN_TYPE", "I_REGISTERS", "I_LOCALS", "I_LABELS", "I_LABEL", "I_ANNOTATIONS", "I_ANNOTATION", "I_ANNOTATION_ELEMENT", "I_SUBANNOTATION", "I_ENCODED_FIELD", "I_ENCODED_METHOD", "I_ENCODED_ENUM", "I_ENCODED_ARRAY", "I_ARRAY_ELEMENT_SIZE", "I_ARRAY_ELEMENTS", "I_PACKED_SWITCH_START_KEY", "I_PACKED_SWITCH_TARGET_COUNT", "I_PACKED_SWITCH_TARGETS", "I_PACKED_SWITCH_DECLARATION", "I_PACKED_SWITCH_DECLARATIONS", "I_SPARSE_SWITCH_KEYS", "I_SPARSE_SWITCH_TARGET_COUNT", "I_SPARSE_SWITCH_TARGETS", "I_SPARSE_SWITCH_DECLARATION", "I_SPARSE_SWITCH_DECLARATIONS", "I_ADDRESS", "I_CATCH", "I_CATCHALL", "I_CATCHES", "I_PARAMETER", "I_PARAMETERS", "I_PARAMETER_NOT_SPECIFIED", "I_ORDERED_DEBUG_DIRECTIVES", "I_LINE", "I_LOCAL", "I_END_LOCAL", "I_RESTART_LOCAL", "I_PROLOGUE", "I_EPILOGUE", "I_STATEMENTS", "I_STATEMENT_FORMAT10t", "I_STATEMENT_FORMAT10x", "I_STATEMENT_FORMAT11n", "I_STATEMENT_FORMAT11x", "I_STATEMENT_FORMAT12x", "I_STATEMENT_FORMAT20t", "I_STATEMENT_FORMAT21c_TYPE", "I_STATEMENT_FORMAT21c_FIELD", "I_STATEMENT_FORMAT21c_STRING", "I_STATEMENT_FORMAT21h", "I_STATEMENT_FORMAT21s", "I_STATEMENT_FORMAT21t", "I_STATEMENT_FORMAT22b", "I_STATEMENT_FORMAT22c_FIELD", "I_STATEMENT_FORMAT22c_TYPE", "I_STATEMENT_FORMAT22s", "I_STATEMENT_FORMAT22t", "I_STATEMENT_FORMAT22x", "I_STATEMENT_FORMAT23x", "I_STATEMENT_FORMAT30t", "I_STATEMENT_FORMAT31c", "I_STATEMENT_FORMAT31i", "I_STATEMENT_FORMAT31t", "I_STATEMENT_FORMAT32x", "I_STATEMENT_FORMAT35c_METHOD", "I_STATEMENT_FORMAT35c_TYPE", "I_STATEMENT_FORMAT3rc_METHOD", "I_STATEMENT_FORMAT3rc_TYPE", "I_STATEMENT_FORMAT51l", "I_STATEMENT_ARRAY_DATA", "I_STATEMENT_PACKED_SWITCH", "I_STATEMENT_SPARSE_SWITCH", "I_REGISTER_RANGE", "I_REGISTER_LIST", "LABEL", "INTEGER_LITERAL", "INVALID_TOKEN"
    };
    public static final int I_REGISTER_LIST=210;
    public static final int I_STATEMENT_FORMAT51l=205;
    public static final int SPARSE_SWITCH_DIRECTIVE=23;
    public static final int I_ORDERED_DEBUG_DIRECTIVES=169;
    public static final int END_PARAMETER_DIRECTIVE=29;
    public static final int BINARY_EXPONENT=39;
    public static final int INSTRUCTION_FORMAT11x=69;
    public static final int ANNOTATION_VISIBILITY=60;
    public static final int INVALID_TOKEN=213;
    public static final int HEX_PREFIX=35;
    public static final int END_SUBANNOTATION_DIRECTIVE=11;
    public static final int I_STATEMENT_FORMAT12x=181;
    public static final int EOF=-1;
    public static final int I_METHOD=135;
    public static final int I_SUBANNOTATION=145;
    public static final int INSTRUCTION_FORMAT11n=68;
    public static final int I_SPARSE_SWITCH_TARGETS=159;
    public static final int I_PACKED_SWITCH_TARGET_COUNT=153;
    public static final int STRING_LITERAL=56;
    public static final int SHORT_LITERAL=47;
    public static final int I_FIELD_INITIAL_VALUE=134;
    public static final int INSTRUCTION_FORMAT10x=67;
    public static final int I_PACKED_SWITCH_DECLARATION=155;
    public static final int I_SUPER=126;
    public static final int I_STATEMENT_FORMAT11n=179;
    public static final int INSTRUCTION_FORMAT10t=66;
    public static final int NEGATIVE_INTEGER_LITERAL=45;
    public static final int REGISTER=59;
    public static final int INSTRUCTION_FORMAT21c_TYPE=75;
    public static final int I_STATEMENT_FORMAT11x=180;
    public static final int LOCAL_DIRECTIVE=30;
    public static final int I_LINE=170;
    public static final int LOCALS_DIRECTIVE=18;
    public static final int I_CATCHALL=164;
    public static final int INSTRUCTION_FORMAT35c_TYPE=95;
    public static final int INSTRUCTION_FORMAT31i_OR_ID=90;
    public static final int I_PARAMETERS=167;
    public static final int I_METHOD_RETURN_TYPE=137;
    public static final int INSTRUCTION_FORMAT51l=101;
    public static final int ENUM_DIRECTIVE=14;
    public static final int NULL_LITERAL=54;
    public static final int REGISTERS_DIRECTIVE=17;
    public static final int BASE_SIMPLE_NAME=102;
    public static final int END_ARRAY_DATA_DIRECTIVE=20;
    public static final int I_STATEMENT_FORMAT21c_FIELD=184;
    public static final int I_METHOD_PROTOTYPE=136;
    public static final int EPILOGUE_DIRECTIVE=34;
    public static final int I_LOCALS=139;
    public static final int I_ENCODED_ARRAY=149;
    public static final int RESTART_LOCAL_DIRECTIVE=32;
    public static final int POSITIVE_INTEGER_LITERAL=44;
    public static final int BASE_PRIMITIVE_TYPE=103;
    public static final int I_CATCH=163;
    public static final int CATCH_DIRECTIVE=25;
    public static final int I_PARAMETER_NOT_SPECIFIED=168;
    public static final int INSTRUCTION_FORMAT21h=76;
    public static final int I_ANNOTATION=143;
    public static final int INSTRUCTION_FORMAT21s=77;
    public static final int INSTRUCTION_FORMAT21t=78;
    public static final int WHITE_SPACE=124;
    public static final int INSTRUCTION_FORMAT22b=79;
    public static final int INSTRUCTION_FORMAT20t=72;
    public static final int OFFSET=64;
    public static final int FLOAT_LITERAL_OR_ID=49;
    public static final int I_PACKED_SWITCH_START_KEY=152;
    public static final int I_ACCESS_LIST=129;
    public static final int I_REGISTERS=138;
    public static final int SOURCE_DIRECTIVE=7;
    public static final int IMPLEMENTS_DIRECTIVE=6;
    public static final int CLASS_DIRECTIVE=4;
    public static final int BASE_CHAR_LITERAL=57;
    public static final int I_STATEMENT_FORMAT35c_METHOD=201;
    public static final int I_STATEMENT_SPARSE_SWITCH=208;
    public static final int BASE_STRING_LITERAL=55;
    public static final int INSTRUCTION_FORMAT23x=87;
    public static final int HEX_DIGIT=36;
    public static final int OPEN_PAREN=122;
    public static final int END_PACKED_SWITCH_DIRECTIVE=22;
    public static final int CLOSE_BRACE=121;
    public static final int I_EPILOGUE=175;
    public static final int I_METHODS=130;
    public static final int LONG_LITERAL=46;
    public static final int INSTRUCTION_FORMAT22c_FIELD=80;
    public static final int ACCESS_SPEC=61;
    public static final int END_FIELD_DIRECTIVE=9;
    public static final int I_IMPLEMENTS=127;
    public static final int COLON=118;
    public static final int INSTRUCTION_FORMAT35ms_METHOD=97;
    public static final int I_STATEMENT_FORMAT23x=195;
    public static final int CHAR_LITERAL=58;
    public static final int INSTRUCTION_FORMAT22s=84;
    public static final int VOID_TYPE=108;
    public static final int INSTRUCTION_FORMAT22t=85;
    public static final int CLASS_DESCRIPTOR=109;
    public static final int ARROW=116;
    public static final int INSTRUCTION_FORMAT22x=86;
    public static final int VTABLE_OFFSET=62;
    public static final int INSTRUCTION_FORMAT12x=71;
    public static final int I_LOCAL=171;
    public static final int I_REGISTER_RANGE=209;
    public static final int INSTRUCTION_FORMAT32x=93;
    public static final int I_STATEMENT_FORMAT22b=189;
    public static final int ANNOTATION_DIRECTIVE=12;
    public static final int END_ANNOTATION_DIRECTIVE=13;
    public static final int I_SPARSE_SWITCH_TARGET_COUNT=158;
    public static final int DOUBLE_LITERAL=52;
    public static final int I_STATEMENT_FORMAT22s=192;
    public static final int I_STATEMENT_FORMAT22t=193;
    public static final int I_STATEMENT_FORMAT32x=200;
    public static final int END_LOCAL_DIRECTIVE=31;
    public static final int BASE_FLOAT_OR_ID=41;
    public static final int BASE_INTEGER=37;
    public static final int I_STATEMENT_FORMAT22x=194;
    public static final int I_STATEMENT_FORMAT21c_STRING=185;
    public static final int INSTRUCTION_FORMAT12x_OR_ID=70;
    public static final int I_STATEMENT_FORMAT31c=197;
    public static final int I_STATEMENT_FORMAT31i=198;
    public static final int INSTRUCTION_FORMAT35s_METHOD=96;
    public static final int I_STATEMENT_FORMAT21h=186;
    public static final int I_ANNOTATIONS=142;
    public static final int PRIMITIVE_TYPE=107;
    public static final int CATCHALL_DIRECTIVE=26;
    public static final int I_STATEMENT_FORMAT21s=187;
    public static final int DOUBLE_LITERAL_OR_ID=50;
    public static final int I_STATEMENT_FORMAT31t=199;
    public static final int I_SOURCE=128;
    public static final int CLOSE_PAREN=123;
    public static final int I_SPARSE_SWITCH_DECLARATIONS=161;
    public static final int ARRAY_DESCRIPTOR=110;
    public static final int I_ADDRESS=162;
    public static final int I_STATEMENT_FORMAT21t=188;
    public static final int I_STATEMENTS=176;
    public static final int SUBANNOTATION_DIRECTIVE=10;
    public static final int INSTRUCTION_FORMAT31c=89;
    public static final int LINE_COMMENT=65;
    public static final int I_PARAMETER=166;
    public static final int I_ARRAY_ELEMENTS=151;
    public static final int I_SPARSE_SWITCH_DECLARATION=160;
    public static final int INSTRUCTION_FORMAT30t=88;
    public static final int BOOL_LITERAL=53;
    public static final int I_STATEMENT_FORMAT21c_TYPE=183;
    public static final int I_STATEMENT_FORMAT20t=182;
    public static final int I_STATEMENT_FORMAT3rc_TYPE=204;
    public static final int BASE_FLOAT=42;
    public static final int METHOD_NAME=114;
    public static final int I_ANNOTATION_ELEMENT=144;
    public static final int PACKED_SWITCH_DIRECTIVE=21;
    public static final int PARAMETER_DIRECTIVE=28;
    public static final int ARRAY_DATA_DIRECTIVE=19;
    public static final int DECIMAL_EXPONENT=38;
    public static final int INTEGER_LITERAL=212;
    public static final int PROLOGUE_DIRECTIVE=33;
    public static final int I_SPARSE_SWITCH_KEYS=157;
    public static final int INSTRUCTION_FORMAT31t=92;
    public static final int I_RESTART_LOCAL=173;
    public static final int INSTRUCTION_FORMAT31i=91;
    public static final int METHOD_DIRECTIVE=15;
    public static final int I_ENCODED_FIELD=146;
    public static final int INSTRUCTION_FORMAT22c_TYPE=81;
    public static final int INSTRUCTION_FORMAT35c_METHOD=94;
    public static final int I_END_LOCAL=172;
    public static final int INSTRUCTION_FORMAT3rc_METHOD=98;
    public static final int BASE_ARRAY_DESCRIPTOR=105;
    public static final int INSTRUCTION_FORMAT22s_OR_ID=83;
    public static final int I_CATCHES=165;
    public static final int SUPER_DIRECTIVE=5;
    public static final int DOTDOT=115;
    public static final int END_SPARSE_SWITCH_DIRECTIVE=24;
    public static final int I_ENCODED_METHOD=147;
    public static final int INSTRUCTION_FORMAT21c_FIELD=73;
    public static final int I_ARRAY_ELEMENT_SIZE=150;
    public static final int I_FIELDS=131;
    public static final int I_LABELS=140;
    public static final int ESCAPE_SEQUENCE=43;
    public static final int I_STATEMENT_FORMAT35c_TYPE=202;
    public static final int BYTE_LITERAL=48;
    public static final int COMMA=119;
    public static final int I_CLASS_DEF=125;
    public static final int EQUAL=117;
    public static final int PARAM_LIST_OR_ID=111;
    public static final int I_FIELD=132;
    public static final int I_STATEMENT_PACKED_SWITCH=207;
    public static final int I_STATEMENT_FORMAT22c_FIELD=190;
    public static final int FIELD_OFFSET=63;
    public static final int FIELD_DIRECTIVE=8;
    public static final int INSTRUCTION_FORMAT3rc_TYPE=99;
    public static final int I_STATEMENT_FORMAT10x=178;
    public static final int I_LABEL=141;
    public static final int I_STATEMENT_ARRAY_DATA=206;
    public static final int END_METHOD_DIRECTIVE=16;
    public static final int I_STATEMENT_FORMAT10t=177;
    public static final int I_PROLOGUE=174;
    public static final int I_ENCODED_ENUM=148;
    public static final int I_PACKED_SWITCH_DECLARATIONS=156;
    public static final int I_FIELD_TYPE=133;
    public static final int I_STATEMENT_FORMAT30t=196;
    public static final int FLOAT_LITERAL=51;
    public static final int INSTRUCTION_FORMAT21c_STRING=74;
    public static final int I_PACKED_SWITCH_TARGETS=154;
    public static final int PARAM_LIST=112;
    public static final int SIMPLE_NAME=113;
    public static final int BASE_CLASS_DESCRIPTOR=104;
    public static final int LINE_DIRECTIVE=27;
    public static final int INSTRUCTION_FORMAT22cs_FIELD=82;
    public static final int LABEL=211;
    public static final int BASE_TYPE=106;
    public static final int I_STATEMENT_FORMAT3rc_METHOD=203;
    public static final int HEX_DIGITS=40;
    public static final int INSTRUCTION_FORMAT3rms_METHOD=100;
    public static final int OPEN_BRACE=120;
    public static final int I_STATEMENT_FORMAT22c_TYPE=191;

    // delegates
    // delegators


        public smaliTreeWalker(TreeNodeStream input) {
            this(input, new RecognizerSharedState());
        }
        public smaliTreeWalker(TreeNodeStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return smaliTreeWalker.tokenNames; }
    public String getGrammarFileName() { return "org/jf/smali/smaliTreeWalker.g"; }


    	public DexFile dexFile;
    	public TypeIdItem classType;

    	private byte parseRegister_nibble(String register, int totalMethodRegisters, int methodParameterRegisters)
    		throws SemanticException {
    		//register should be in the format "v12"
    		int val = Byte.parseByte(register.substring(1));
    		if (register.charAt(0) == 'p') {
    			val = totalMethodRegisters - methodParameterRegisters + val;
    		}
    		if (val >= 2<<4) {
    			throw new SemanticException(input, "The maximum allowed register in this context is list of registers is v15");
    		}
    		//the parser wouldn't have accepted a negative register, i.e. v-1, so we don't have to check for val<0;
    		return (byte)val;
    	}

    	//return a short, because java's byte is signed
    	private short parseRegister_byte(String register, int totalMethodRegisters, int methodParameterRegisters)
    		throws SemanticException {
    		//register should be in the format "v123"
    		int val = Short.parseShort(register.substring(1));
    		if (register.charAt(0) == 'p') {
    			val = totalMethodRegisters - methodParameterRegisters + val;
    		}
    		if (val >= 2<<8) {
    			throw new SemanticException(input, "The maximum allowed register in this context is v255");
    		}
    		return (short)val;
    	}

    	//return an int because java's short is signed
    	private int parseRegister_short(String register, int totalMethodRegisters, int methodParameterRegisters)
    		throws SemanticException {
    		//register should be in the format "v12345"
    		int val = Integer.parseInt(register.substring(1));
    		if (register.charAt(0) == 'p') {
    			val = totalMethodRegisters - methodParameterRegisters + val;
    		}
    		if (val >= 2<<16) {
    			throw new SemanticException(input, "The maximum allowed register in this context is v65535");
    		}
    		//the parser wouldn't accept a negative register, i.e. v-1, so we don't have to check for val<0;
    		return val;
    	}

    	private static Pattern specialFloatRegex = Pattern.compile("((-)?infinityf)|(nanf)", Pattern.CASE_INSENSITIVE);
    	private float parseFloat(String floatString) {
    		Matcher m = specialFloatRegex.matcher(floatString);
    		if (m.matches()) {
    			//got an infinity
    			if (m.start(1) != -1) {
    				if (m.start(2) != -1) {
    					return Float.NEGATIVE_INFINITY;
    				} else {
    					return Float.POSITIVE_INFINITY;
    				}
    			} else {
    				return Float.NaN;
    			}
    		}
    		return Float.parseFloat(floatString);
    	}

    	private static Pattern specialDoubleRegex = Pattern.compile("((-)?infinityd?)|(nand?)", Pattern.CASE_INSENSITIVE);
    	private double parseDouble(String doubleString) {
    		Matcher m = specialDoubleRegex.matcher(doubleString);
    		if (m.matches()) {
    			//got an infinity
    			if (m.start(1) != -1) {
    				if (m.start(2) != -1) {
    					return Double.NEGATIVE_INFINITY;
    				} else {
    					return Double.POSITIVE_INFINITY;
    				}
    			} else {
    				return Double.NaN;
    			}
    		}
    		return Double.parseDouble(doubleString);
    	}

    	public String getErrorMessage(RecognitionException e, String[] tokenNames) {
    		if ( e instanceof SemanticException ) {
    			return e.getMessage();
    		} else {
    			return super.getErrorMessage(e, tokenNames);
    		}
    	}

    	public String getErrorHeader(RecognitionException e) {
    		return getSourceName()+"["+ e.line+","+e.charPositionInLine+"]";
    	}



    // $ANTLR start "smali_file"
    // org/jf/smali/smaliTreeWalker.g:150:1: smali_file : ^( I_CLASS_DEF header methods fields annotations ) ;
    public final void smali_file() throws RecognitionException {
        smaliTreeWalker.methods_return methods1 = null;

        smaliTreeWalker.fields_return fields2 = null;

        AnnotationSetItem annotations3 = null;

        smaliTreeWalker.header_return header4 = null;


        try {
            // org/jf/smali/smaliTreeWalker.g:151:2: ( ^( I_CLASS_DEF header methods fields annotations ) )
            // org/jf/smali/smaliTreeWalker.g:151:4: ^( I_CLASS_DEF header methods fields annotations )
            {
            match(input,I_CLASS_DEF,FOLLOW_I_CLASS_DEF_in_smali_file48); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_header_in_smali_file50);
            header4=header();

            state._fsp--;

            pushFollow(FOLLOW_methods_in_smali_file52);
            methods1=methods();

            state._fsp--;

            pushFollow(FOLLOW_fields_in_smali_file54);
            fields2=fields();

            state._fsp--;

            pushFollow(FOLLOW_annotations_in_smali_file56);
            annotations3=annotations();

            state._fsp--;


            match(input, Token.UP, null); 

            		AnnotationDirectoryItem annotationDirectoryItem = null;
            		ClassDefItem classDefItem = null;
            		ClassDataItem classDataItem = null;

            		if (	(methods1!=null?methods1.methodAnnotations:null) != null ||
            			(methods1!=null?methods1.parameterAnnotations:null) != null ||
            			(fields2!=null?fields2.fieldAnnotations:null) != null ||
            			annotations3 != null) {
            			annotationDirectoryItem = AnnotationDirectoryItem.internAnnotationDirectoryItem(
            				dexFile,
            				annotations3,
            				(fields2!=null?fields2.fieldAnnotations:null),
            				(methods1!=null?methods1.methodAnnotations:null),
            				(methods1!=null?methods1.parameterAnnotations:null));
            		}

            		if ((fields2!=null?fields2.staticFields:null).size() != 0 || (fields2!=null?fields2.instanceFields:null).size() != 0 ||
            		    (methods1!=null?methods1.directMethods:null).size() != 0 || (methods1!=null?methods1.virtualMethods:null).size()!= 0) {
            			classDataItem = ClassDataItem.internClassDataItem(dexFile, (fields2!=null?fields2.staticFields:null), (fields2!=null?fields2.instanceFields:null),
            									(methods1!=null?methods1.directMethods:null), (methods1!=null?methods1.virtualMethods:null));
            		}

            		classDefItem = ClassDefItem.internClassDefItem(dexFile, (header4!=null?header4.classType:null), (header4!=null?header4.accessFlags:0),
            				(header4!=null?header4.superType:null), (header4!=null?header4.implementsList:null), (header4!=null?header4.sourceSpec:null), annotationDirectoryItem,
            				classDataItem, (fields2!=null?fields2.staticFieldInitialValues:null));
            	

            }

        }
        catch (Exception ex) {

            		reportError(new SemanticException(input, ex));
            	
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "smali_file"

    public static class header_return extends TreeRuleReturnScope {
        public TypeIdItem classType;
        public int accessFlags;
        public TypeIdItem superType;
        public TypeListItem implementsList;
        public StringIdItem sourceSpec;
    };

    // $ANTLR start "header"
    // org/jf/smali/smaliTreeWalker.g:185:1: header returns [TypeIdItem classType, int accessFlags, TypeIdItem superType, TypeListItem implementsList, StringIdItem sourceSpec] : class_spec ( super_spec )? implements_list source_spec ;
    public final smaliTreeWalker.header_return header() throws RecognitionException {
        smaliTreeWalker.header_return retval = new smaliTreeWalker.header_return();
        retval.start = input.LT(1);

        smaliTreeWalker.class_spec_return class_spec5 = null;

        TypeIdItem super_spec6 = null;

        TypeListItem implements_list7 = null;

        StringIdItem source_spec8 = null;


        try {
            // org/jf/smali/smaliTreeWalker.g:186:1: ( class_spec ( super_spec )? implements_list source_spec )
            // org/jf/smali/smaliTreeWalker.g:186:3: class_spec ( super_spec )? implements_list source_spec
            {
            pushFollow(FOLLOW_class_spec_in_header80);
            class_spec5=class_spec();

            state._fsp--;

            // org/jf/smali/smaliTreeWalker.g:186:14: ( super_spec )?
            int alt1=2;
            switch ( input.LA(1) ) {
                case I_SUPER:
                    {
                    alt1=1;
                    }
                    break;
            }

            switch (alt1) {
                case 1 :
                    // org/jf/smali/smaliTreeWalker.g:186:14: super_spec
                    {
                    pushFollow(FOLLOW_super_spec_in_header82);
                    super_spec6=super_spec();

                    state._fsp--;


                    }
                    break;

            }

            pushFollow(FOLLOW_implements_list_in_header85);
            implements_list7=implements_list();

            state._fsp--;

            pushFollow(FOLLOW_source_spec_in_header87);
            source_spec8=source_spec();

            state._fsp--;


            		classType = (class_spec5!=null?class_spec5.type:null);
            		retval.classType = classType;
            		retval.accessFlags = (class_spec5!=null?class_spec5.accessFlags:0);
            		retval.superType = super_spec6;
            		retval.implementsList = implements_list7;
            		retval.sourceSpec = source_spec8;
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "header"

    public static class class_spec_return extends TreeRuleReturnScope {
        public TypeIdItem type;
        public int accessFlags;
    };

    // $ANTLR start "class_spec"
    // org/jf/smali/smaliTreeWalker.g:197:1: class_spec returns [TypeIdItem type, int accessFlags] : class_type_descriptor access_list ;
    public final smaliTreeWalker.class_spec_return class_spec() throws RecognitionException {
        smaliTreeWalker.class_spec_return retval = new smaliTreeWalker.class_spec_return();
        retval.start = input.LT(1);

        TypeIdItem class_type_descriptor9 = null;

        int access_list10 = 0;


        try {
            // org/jf/smali/smaliTreeWalker.g:198:2: ( class_type_descriptor access_list )
            // org/jf/smali/smaliTreeWalker.g:198:4: class_type_descriptor access_list
            {
            pushFollow(FOLLOW_class_type_descriptor_in_class_spec103);
            class_type_descriptor9=class_type_descriptor();

            state._fsp--;

            pushFollow(FOLLOW_access_list_in_class_spec105);
            access_list10=access_list();

            state._fsp--;


            		retval.type = class_type_descriptor9;
            		retval.accessFlags = access_list10;
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "class_spec"


    // $ANTLR start "super_spec"
    // org/jf/smali/smaliTreeWalker.g:204:1: super_spec returns [TypeIdItem type] : ^( I_SUPER class_type_descriptor ) ;
    public final TypeIdItem super_spec() throws RecognitionException {
        TypeIdItem type = null;

        TypeIdItem class_type_descriptor11 = null;


        try {
            // org/jf/smali/smaliTreeWalker.g:205:2: ( ^( I_SUPER class_type_descriptor ) )
            // org/jf/smali/smaliTreeWalker.g:205:4: ^( I_SUPER class_type_descriptor )
            {
            match(input,I_SUPER,FOLLOW_I_SUPER_in_super_spec121); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_class_type_descriptor_in_super_spec123);
            class_type_descriptor11=class_type_descriptor();

            state._fsp--;


            match(input, Token.UP, null); 

            		type = class_type_descriptor11;
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return type;
    }
    // $ANTLR end "super_spec"


    // $ANTLR start "implements_spec"
    // org/jf/smali/smaliTreeWalker.g:211:1: implements_spec returns [TypeIdItem type] : ^( I_IMPLEMENTS class_type_descriptor ) ;
    public final TypeIdItem implements_spec() throws RecognitionException {
        TypeIdItem type = null;

        TypeIdItem class_type_descriptor12 = null;


        try {
            // org/jf/smali/smaliTreeWalker.g:212:2: ( ^( I_IMPLEMENTS class_type_descriptor ) )
            // org/jf/smali/smaliTreeWalker.g:212:4: ^( I_IMPLEMENTS class_type_descriptor )
            {
            match(input,I_IMPLEMENTS,FOLLOW_I_IMPLEMENTS_in_implements_spec141); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_class_type_descriptor_in_implements_spec143);
            class_type_descriptor12=class_type_descriptor();

            state._fsp--;


            match(input, Token.UP, null); 

            		type = class_type_descriptor12;
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return type;
    }
    // $ANTLR end "implements_spec"


    // $ANTLR start "implements_list"
    // org/jf/smali/smaliTreeWalker.g:217:1: implements_list returns [TypeListItem implementsList] : ( implements_spec )* ;
    public final TypeListItem implements_list() throws RecognitionException {
        TypeListItem implementsList = null;

        TypeIdItem implements_spec13 = null;


         List<TypeIdItem> typeList; 
        try {
            // org/jf/smali/smaliTreeWalker.g:219:2: ( ( implements_spec )* )
            // org/jf/smali/smaliTreeWalker.g:219:4: ( implements_spec )*
            {
            typeList = new LinkedList<TypeIdItem>();
            // org/jf/smali/smaliTreeWalker.g:220:3: ( implements_spec )*
            loop2:
            do {
                int alt2=2;
                switch ( input.LA(1) ) {
                case I_IMPLEMENTS:
                    {
                    alt2=1;
                    }
                    break;

                }

                switch (alt2) {
            	case 1 :
            	    // org/jf/smali/smaliTreeWalker.g:220:4: implements_spec
            	    {
            	    pushFollow(FOLLOW_implements_spec_in_implements_list169);
            	    implements_spec13=implements_spec();

            	    state._fsp--;

            	    typeList.add(implements_spec13);

            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


            		if (typeList.size() > 0) {
            			implementsList = TypeListItem.internTypeListItem(dexFile, typeList);
            		} else {
            			implementsList = null;
            		}
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return implementsList;
    }
    // $ANTLR end "implements_list"


    // $ANTLR start "source_spec"
    // org/jf/smali/smaliTreeWalker.g:229:1: source_spec returns [StringIdItem source] : ( ^( I_SOURCE string_literal ) | );
    public final StringIdItem source_spec() throws RecognitionException {
        StringIdItem source = null;

        String string_literal14 = null;


        try {
            // org/jf/smali/smaliTreeWalker.g:230:2: ( ^( I_SOURCE string_literal ) | )
            int alt3=2;
            switch ( input.LA(1) ) {
            case I_SOURCE:
                {
                alt3=1;
                }
                break;
            case I_METHODS:
                {
                alt3=2;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }

            switch (alt3) {
                case 1 :
                    // org/jf/smali/smaliTreeWalker.g:230:4: ^( I_SOURCE string_literal )
                    {
                    source = null;
                    match(input,I_SOURCE,FOLLOW_I_SOURCE_in_source_spec194); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_string_literal_in_source_spec196);
                    string_literal14=string_literal();

                    state._fsp--;

                    source = StringIdItem.internStringIdItem(dexFile, string_literal14);

                    match(input, Token.UP, null); 

                    }
                    break;
                case 2 :
                    // org/jf/smali/smaliTreeWalker.g:232:4: 
                    {
                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return source;
    }
    // $ANTLR end "source_spec"


    // $ANTLR start "access_list"
    // org/jf/smali/smaliTreeWalker.g:236:1: access_list returns [int value] : ^( I_ACCESS_LIST ( ACCESS_SPEC )* ) ;
    public final int access_list() throws RecognitionException {
        int value = 0;

        CommonTree ACCESS_SPEC15=null;


        		value = 0;
        	
        try {
            // org/jf/smali/smaliTreeWalker.g:241:2: ( ^( I_ACCESS_LIST ( ACCESS_SPEC )* ) )
            // org/jf/smali/smaliTreeWalker.g:241:4: ^( I_ACCESS_LIST ( ACCESS_SPEC )* )
            {
            match(input,I_ACCESS_LIST,FOLLOW_I_ACCESS_LIST_in_access_list226); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // org/jf/smali/smaliTreeWalker.g:242:4: ( ACCESS_SPEC )*
                loop4:
                do {
                    int alt4=2;
                    switch ( input.LA(1) ) {
                    case ACCESS_SPEC:
                        {
                        alt4=1;
                        }
                        break;

                    }

                    switch (alt4) {
                	case 1 :
                	    // org/jf/smali/smaliTreeWalker.g:243:5: ACCESS_SPEC
                	    {
                	    ACCESS_SPEC15=(CommonTree)match(input,ACCESS_SPEC,FOLLOW_ACCESS_SPEC_in_access_list237); 

                	    					value |= AccessFlags.getAccessFlag(ACCESS_SPEC15.getText()).getValue();
                	    				

                	    }
                	    break;

                	default :
                	    break loop4;
                    }
                } while (true);


                match(input, Token.UP, null); 
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return value;
    }
    // $ANTLR end "access_list"

    public static class fields_return extends TreeRuleReturnScope {
        public List<ClassDataItem.EncodedField> staticFields;
        public List<ClassDataItem.EncodedField> instanceFields;
        public List<ClassDefItem.StaticFieldInitializer> staticFieldInitialValues;
        public List<AnnotationDirectoryItem.FieldAnnotation> fieldAnnotations;
    };

    // $ANTLR start "fields"
    // org/jf/smali/smaliTreeWalker.g:250:1: fields returns [List<ClassDataItem.EncodedField> staticFields, List<ClassDataItem.EncodedField> instanceFields,\n\t List<ClassDefItem.StaticFieldInitializer> staticFieldInitialValues, List<AnnotationDirectoryItem.FieldAnnotation> fieldAnnotations] : ^( I_FIELDS ( field )* ) ;
    public final smaliTreeWalker.fields_return fields() throws RecognitionException {
        smaliTreeWalker.fields_return retval = new smaliTreeWalker.fields_return();
        retval.start = input.LT(1);

        smaliTreeWalker.field_return field16 = null;



        		retval.staticFields = new LinkedList<ClassDataItem.EncodedField>();
        		retval.instanceFields = new LinkedList<ClassDataItem.EncodedField>();
        		retval.staticFieldInitialValues = new LinkedList<ClassDefItem.StaticFieldInitializer>();
        	
        try {
            // org/jf/smali/smaliTreeWalker.g:258:2: ( ^( I_FIELDS ( field )* ) )
            // org/jf/smali/smaliTreeWalker.g:258:4: ^( I_FIELDS ( field )* )
            {
            match(input,I_FIELDS,FOLLOW_I_FIELDS_in_fields271); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // org/jf/smali/smaliTreeWalker.g:259:4: ( field )*
                loop5:
                do {
                    int alt5=2;
                    switch ( input.LA(1) ) {
                    case I_FIELD:
                        {
                        alt5=1;
                        }
                        break;

                    }

                    switch (alt5) {
                	case 1 :
                	    // org/jf/smali/smaliTreeWalker.g:259:5: field
                	    {
                	    pushFollow(FOLLOW_field_in_fields277);
                	    field16=field();

                	    state._fsp--;


                	    				if ((field16!=null?field16.encodedField:null).isStatic()) {
                	    					retval.staticFields.add((field16!=null?field16.encodedField:null));
                	    					retval.staticFieldInitialValues.add(new ClassDefItem.StaticFieldInitializer(
                	    						(field16!=null?field16.encodedValue:null), (field16!=null?field16.encodedField:null)));
                	    				} else {
                	    					retval.instanceFields.add((field16!=null?field16.encodedField:null));
                	    				}
                	    				if ((field16!=null?field16.fieldAnnotationSet:null) != null) {
                	    					if (retval.fieldAnnotations == null) {
                	    						retval.fieldAnnotations = new LinkedList<AnnotationDirectoryItem.FieldAnnotation>();
                	    					}
                	    					AnnotationDirectoryItem.FieldAnnotation fieldAnnotation = new AnnotationDirectoryItem.FieldAnnotation(
                	    						(field16!=null?field16.encodedField:null).field, (field16!=null?field16.fieldAnnotationSet:null));
                	    					retval.fieldAnnotations.add(fieldAnnotation);
                	    				}
                	    			

                	    }
                	    break;

                	default :
                	    break loop5;
                    }
                } while (true);


                match(input, Token.UP, null); 
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "fields"

    public static class methods_return extends TreeRuleReturnScope {
        public List<ClassDataItem.EncodedMethod> directMethods;
        public List<ClassDataItem.EncodedMethod> virtualMethods;
        public List<AnnotationDirectoryItem.MethodAnnotation> methodAnnotations;
        public List<AnnotationDirectoryItem.ParameterAnnotation> parameterAnnotations;
    };

    // $ANTLR start "methods"
    // org/jf/smali/smaliTreeWalker.g:278:1: methods returns [List<ClassDataItem.EncodedMethod> directMethods,\n\t\tList<ClassDataItem.EncodedMethod> virtualMethods,\n\t\tList<AnnotationDirectoryItem.MethodAnnotation> methodAnnotations,\n\t\tList<AnnotationDirectoryItem.ParameterAnnotation> parameterAnnotations] : ^( I_METHODS ( method )* ) ;
    public final smaliTreeWalker.methods_return methods() throws RecognitionException {
        smaliTreeWalker.methods_return retval = new smaliTreeWalker.methods_return();
        retval.start = input.LT(1);

        smaliTreeWalker.method_return method17 = null;



        		retval.directMethods = new LinkedList<ClassDataItem.EncodedMethod>();
        		retval.virtualMethods = new LinkedList<ClassDataItem.EncodedMethod>();
        	
        try {
            // org/jf/smali/smaliTreeWalker.g:287:2: ( ^( I_METHODS ( method )* ) )
            // org/jf/smali/smaliTreeWalker.g:287:4: ^( I_METHODS ( method )* )
            {
            match(input,I_METHODS,FOLLOW_I_METHODS_in_methods305); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // org/jf/smali/smaliTreeWalker.g:288:4: ( method )*
                loop6:
                do {
                    int alt6=2;
                    switch ( input.LA(1) ) {
                    case I_METHOD:
                        {
                        alt6=1;
                        }
                        break;

                    }

                    switch (alt6) {
                	case 1 :
                	    // org/jf/smali/smaliTreeWalker.g:288:5: method
                	    {
                	    pushFollow(FOLLOW_method_in_methods311);
                	    method17=method();

                	    state._fsp--;


                	    				if ((method17!=null?method17.encodedMethod:null).isDirect()) {
                	    					retval.directMethods.add((method17!=null?method17.encodedMethod:null));
                	    				} else {
                	    					retval.virtualMethods.add((method17!=null?method17.encodedMethod:null));
                	    				}
                	    				if ((method17!=null?method17.methodAnnotationSet:null) != null) {
                	    					if (retval.methodAnnotations == null) {
                	    						retval.methodAnnotations = new LinkedList<AnnotationDirectoryItem.MethodAnnotation>();
                	    					}
                	    					AnnotationDirectoryItem.MethodAnnotation methodAnnotation =
                	    						new AnnotationDirectoryItem.MethodAnnotation((method17!=null?method17.encodedMethod:null).method, (method17!=null?method17.methodAnnotationSet:null));
                	    					retval.methodAnnotations.add(methodAnnotation);
                	    				}
                	    				if ((method17!=null?method17.parameterAnnotationSets:null) != null) {
                	    					if (retval.parameterAnnotations == null) {
                	    						retval.parameterAnnotations = new LinkedList<AnnotationDirectoryItem.ParameterAnnotation>();
                	    					}
                	    					AnnotationDirectoryItem.ParameterAnnotation parameterAnnotation =
                	    						new AnnotationDirectoryItem.ParameterAnnotation((method17!=null?method17.encodedMethod:null).method,
                	    							(method17!=null?method17.parameterAnnotationSets:null));
                	    					retval.parameterAnnotations.add(parameterAnnotation);
                	    				}
                	    			

                	    }
                	    break;

                	default :
                	    break loop6;
                    }
                } while (true);


                match(input, Token.UP, null); 
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "methods"

    public static class field_return extends TreeRuleReturnScope {
        public ClassDataItem.EncodedField encodedField;
        public EncodedValue encodedValue;
        public AnnotationSetItem fieldAnnotationSet;
    };

    // $ANTLR start "field"
    // org/jf/smali/smaliTreeWalker.g:314:1: field returns [ClassDataItem.EncodedField encodedField, EncodedValue encodedValue, AnnotationSetItem fieldAnnotationSet] : ^( I_FIELD SIMPLE_NAME access_list ^( I_FIELD_TYPE nonvoid_type_descriptor ) field_initial_value ( annotations )? ) ;
    public final smaliTreeWalker.field_return field() throws RecognitionException {
        smaliTreeWalker.field_return retval = new smaliTreeWalker.field_return();
        retval.start = input.LT(1);

        CommonTree SIMPLE_NAME18=null;
        smaliTreeWalker.nonvoid_type_descriptor_return nonvoid_type_descriptor19 = null;

        int access_list20 = 0;

        EncodedValue field_initial_value21 = null;

        AnnotationSetItem annotations22 = null;


        try {
            // org/jf/smali/smaliTreeWalker.g:315:2: ( ^( I_FIELD SIMPLE_NAME access_list ^( I_FIELD_TYPE nonvoid_type_descriptor ) field_initial_value ( annotations )? ) )
            // org/jf/smali/smaliTreeWalker.g:315:3: ^( I_FIELD SIMPLE_NAME access_list ^( I_FIELD_TYPE nonvoid_type_descriptor ) field_initial_value ( annotations )? )
            {
            match(input,I_FIELD,FOLLOW_I_FIELD_in_field332); 

            match(input, Token.DOWN, null); 
            SIMPLE_NAME18=(CommonTree)match(input,SIMPLE_NAME,FOLLOW_SIMPLE_NAME_in_field334); 
            pushFollow(FOLLOW_access_list_in_field336);
            access_list20=access_list();

            state._fsp--;

            match(input,I_FIELD_TYPE,FOLLOW_I_FIELD_TYPE_in_field339); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_nonvoid_type_descriptor_in_field341);
            nonvoid_type_descriptor19=nonvoid_type_descriptor();

            state._fsp--;


            match(input, Token.UP, null); 
            pushFollow(FOLLOW_field_initial_value_in_field344);
            field_initial_value21=field_initial_value();

            state._fsp--;

            // org/jf/smali/smaliTreeWalker.g:315:97: ( annotations )?
            int alt7=2;
            switch ( input.LA(1) ) {
                case I_ANNOTATIONS:
                    {
                    alt7=1;
                    }
                    break;
            }

            switch (alt7) {
                case 1 :
                    // org/jf/smali/smaliTreeWalker.g:315:97: annotations
                    {
                    pushFollow(FOLLOW_annotations_in_field346);
                    annotations22=annotations();

                    state._fsp--;


                    }
                    break;

            }


            match(input, Token.UP, null); 

            		StringIdItem memberName = StringIdItem.internStringIdItem(dexFile, (SIMPLE_NAME18!=null?SIMPLE_NAME18.getText():null));
            		TypeIdItem fieldType = (nonvoid_type_descriptor19!=null?nonvoid_type_descriptor19.type:null);

            		FieldIdItem fieldIdItem = FieldIdItem.internFieldIdItem(dexFile, classType, fieldType, memberName);
            		retval.encodedField = new ClassDataItem.EncodedField(fieldIdItem, access_list20);

            		if (field_initial_value21 != null) {
            			if (!retval.encodedField.isStatic()) {
            				throw new SemanticException(input, "Initial field values can only be specified for static fields.");
            			}

            			retval.encodedValue = field_initial_value21;
            		} else {
            			retval.encodedValue = null;
            		}

            		if (annotations22 != null) {
            			retval.fieldAnnotationSet = annotations22;
            		}
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "field"


    // $ANTLR start "field_initial_value"
    // org/jf/smali/smaliTreeWalker.g:339:1: field_initial_value returns [EncodedValue encodedValue] : ( ^( I_FIELD_INITIAL_VALUE literal ) | );
    public final EncodedValue field_initial_value() throws RecognitionException {
        EncodedValue encodedValue = null;

        EncodedValue literal23 = null;


        try {
            // org/jf/smali/smaliTreeWalker.g:340:2: ( ^( I_FIELD_INITIAL_VALUE literal ) | )
            int alt8=2;
            switch ( input.LA(1) ) {
            case I_FIELD_INITIAL_VALUE:
                {
                alt8=1;
                }
                break;
            case UP:
            case I_ANNOTATIONS:
                {
                alt8=2;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }

            switch (alt8) {
                case 1 :
                    // org/jf/smali/smaliTreeWalker.g:340:4: ^( I_FIELD_INITIAL_VALUE literal )
                    {
                    match(input,I_FIELD_INITIAL_VALUE,FOLLOW_I_FIELD_INITIAL_VALUE_in_field_initial_value365); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_literal_in_field_initial_value367);
                    literal23=literal();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    encodedValue = literal23;

                    }
                    break;
                case 2 :
                    // org/jf/smali/smaliTreeWalker.g:341:4: 
                    {
                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return encodedValue;
    }
    // $ANTLR end "field_initial_value"


    // $ANTLR start "literal"
    // org/jf/smali/smaliTreeWalker.g:343:1: literal returns [EncodedValue encodedValue] : ( integer_literal | long_literal | short_literal | byte_literal | float_literal | double_literal | char_literal | string_literal | bool_literal | NULL_LITERAL | type_descriptor | array_literal | subannotation | field_literal | method_literal | enum_literal );
    public final EncodedValue literal() throws RecognitionException {
        EncodedValue encodedValue = null;

        int integer_literal24 = 0;

        long long_literal25 = 0;

        short short_literal26 = 0;

        byte byte_literal27 = 0;

        float float_literal28 = 0.0f;

        double double_literal29 = 0.0;

        char char_literal30 = 0;

        String string_literal31 = null;

        boolean bool_literal32 = false;

        TypeIdItem type_descriptor33 = null;

        EncodedValue[] array_literal34 = null;

        smaliTreeWalker.subannotation_return subannotation35 = null;

        FieldIdItem field_literal36 = null;

        MethodIdItem method_literal37 = null;

        FieldIdItem enum_literal38 = null;


        try {
            // org/jf/smali/smaliTreeWalker.g:344:2: ( integer_literal | long_literal | short_literal | byte_literal | float_literal | double_literal | char_literal | string_literal | bool_literal | NULL_LITERAL | type_descriptor | array_literal | subannotation | field_literal | method_literal | enum_literal )
            int alt9=16;
            switch ( input.LA(1) ) {
            case INTEGER_LITERAL:
                {
                alt9=1;
                }
                break;
            case LONG_LITERAL:
                {
                alt9=2;
                }
                break;
            case SHORT_LITERAL:
                {
                alt9=3;
                }
                break;
            case BYTE_LITERAL:
                {
                alt9=4;
                }
                break;
            case FLOAT_LITERAL:
                {
                alt9=5;
                }
                break;
            case DOUBLE_LITERAL:
                {
                alt9=6;
                }
                break;
            case CHAR_LITERAL:
                {
                alt9=7;
                }
                break;
            case STRING_LITERAL:
                {
                alt9=8;
                }
                break;
            case BOOL_LITERAL:
                {
                alt9=9;
                }
                break;
            case NULL_LITERAL:
                {
                alt9=10;
                }
                break;
            case PRIMITIVE_TYPE:
            case VOID_TYPE:
            case CLASS_DESCRIPTOR:
            case ARRAY_DESCRIPTOR:
                {
                alt9=11;
                }
                break;
            case I_ENCODED_ARRAY:
                {
                alt9=12;
                }
                break;
            case I_SUBANNOTATION:
                {
                alt9=13;
                }
                break;
            case I_ENCODED_FIELD:
                {
                alt9=14;
                }
                break;
            case I_ENCODED_METHOD:
                {
                alt9=15;
                }
                break;
            case I_ENCODED_ENUM:
                {
                alt9=16;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;
            }

            switch (alt9) {
                case 1 :
                    // org/jf/smali/smaliTreeWalker.g:344:4: integer_literal
                    {
                    pushFollow(FOLLOW_integer_literal_in_literal386);
                    integer_literal24=integer_literal();

                    state._fsp--;

                     encodedValue = new IntEncodedValue(integer_literal24); 

                    }
                    break;
                case 2 :
                    // org/jf/smali/smaliTreeWalker.g:345:4: long_literal
                    {
                    pushFollow(FOLLOW_long_literal_in_literal393);
                    long_literal25=long_literal();

                    state._fsp--;

                     encodedValue = new LongEncodedValue(long_literal25); 

                    }
                    break;
                case 3 :
                    // org/jf/smali/smaliTreeWalker.g:346:4: short_literal
                    {
                    pushFollow(FOLLOW_short_literal_in_literal400);
                    short_literal26=short_literal();

                    state._fsp--;

                     encodedValue = new ShortEncodedValue(short_literal26); 

                    }
                    break;
                case 4 :
                    // org/jf/smali/smaliTreeWalker.g:347:4: byte_literal
                    {
                    pushFollow(FOLLOW_byte_literal_in_literal407);
                    byte_literal27=byte_literal();

                    state._fsp--;

                     encodedValue = new ByteEncodedValue(byte_literal27); 

                    }
                    break;
                case 5 :
                    // org/jf/smali/smaliTreeWalker.g:348:4: float_literal
                    {
                    pushFollow(FOLLOW_float_literal_in_literal414);
                    float_literal28=float_literal();

                    state._fsp--;

                     encodedValue = new FloatEncodedValue(float_literal28); 

                    }
                    break;
                case 6 :
                    // org/jf/smali/smaliTreeWalker.g:349:4: double_literal
                    {
                    pushFollow(FOLLOW_double_literal_in_literal421);
                    double_literal29=double_literal();

                    state._fsp--;

                     encodedValue = new DoubleEncodedValue(double_literal29); 

                    }
                    break;
                case 7 :
                    // org/jf/smali/smaliTreeWalker.g:350:4: char_literal
                    {
                    pushFollow(FOLLOW_char_literal_in_literal428);
                    char_literal30=char_literal();

                    state._fsp--;

                     encodedValue = new CharEncodedValue(char_literal30); 

                    }
                    break;
                case 8 :
                    // org/jf/smali/smaliTreeWalker.g:351:4: string_literal
                    {
                    pushFollow(FOLLOW_string_literal_in_literal435);
                    string_literal31=string_literal();

                    state._fsp--;

                     encodedValue = new StringEncodedValue(StringIdItem.internStringIdItem(dexFile, string_literal31)); 

                    }
                    break;
                case 9 :
                    // org/jf/smali/smaliTreeWalker.g:352:4: bool_literal
                    {
                    pushFollow(FOLLOW_bool_literal_in_literal442);
                    bool_literal32=bool_literal();

                    state._fsp--;

                     encodedValue = bool_literal32?BooleanEncodedValue.TrueValue:BooleanEncodedValue.FalseValue; 

                    }
                    break;
                case 10 :
                    // org/jf/smali/smaliTreeWalker.g:353:4: NULL_LITERAL
                    {
                    match(input,NULL_LITERAL,FOLLOW_NULL_LITERAL_in_literal449); 
                     encodedValue = NullEncodedValue.NullValue; 

                    }
                    break;
                case 11 :
                    // org/jf/smali/smaliTreeWalker.g:354:4: type_descriptor
                    {
                    pushFollow(FOLLOW_type_descriptor_in_literal456);
                    type_descriptor33=type_descriptor();

                    state._fsp--;

                     encodedValue = new TypeEncodedValue(type_descriptor33); 

                    }
                    break;
                case 12 :
                    // org/jf/smali/smaliTreeWalker.g:355:4: array_literal
                    {
                    pushFollow(FOLLOW_array_literal_in_literal463);
                    array_literal34=array_literal();

                    state._fsp--;

                     encodedValue = new ArrayEncodedValue(array_literal34); 

                    }
                    break;
                case 13 :
                    // org/jf/smali/smaliTreeWalker.g:356:4: subannotation
                    {
                    pushFollow(FOLLOW_subannotation_in_literal470);
                    subannotation35=subannotation();

                    state._fsp--;

                     encodedValue = new AnnotationEncodedValue((subannotation35!=null?subannotation35.annotationType:null), (subannotation35!=null?subannotation35.elementNames:null), (subannotation35!=null?subannotation35.elementValues:null)); 

                    }
                    break;
                case 14 :
                    // org/jf/smali/smaliTreeWalker.g:357:4: field_literal
                    {
                    pushFollow(FOLLOW_field_literal_in_literal477);
                    field_literal36=field_literal();

                    state._fsp--;

                     encodedValue = new FieldEncodedValue(field_literal36); 

                    }
                    break;
                case 15 :
                    // org/jf/smali/smaliTreeWalker.g:358:4: method_literal
                    {
                    pushFollow(FOLLOW_method_literal_in_literal484);
                    method_literal37=method_literal();

                    state._fsp--;

                     encodedValue = new MethodEncodedValue(method_literal37); 

                    }
                    break;
                case 16 :
                    // org/jf/smali/smaliTreeWalker.g:359:4: enum_literal
                    {
                    pushFollow(FOLLOW_enum_literal_in_literal491);
                    enum_literal38=enum_literal();

                    state._fsp--;

                     encodedValue = new EnumEncodedValue(enum_literal38); 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return encodedValue;
    }
    // $ANTLR end "literal"


    // $ANTLR start "fixed_size_literal"
    // org/jf/smali/smaliTreeWalker.g:363:1: fixed_size_literal returns [byte[] value] : ( integer_literal | long_literal | short_literal | byte_literal | float_literal | double_literal | char_literal | bool_literal );
    public final byte[] fixed_size_literal() throws RecognitionException {
        byte[] value = null;

        int integer_literal39 = 0;

        long long_literal40 = 0;

        short short_literal41 = 0;

        byte byte_literal42 = 0;

        float float_literal43 = 0.0f;

        double double_literal44 = 0.0;

        char char_literal45 = 0;

        boolean bool_literal46 = false;


        try {
            // org/jf/smali/smaliTreeWalker.g:364:2: ( integer_literal | long_literal | short_literal | byte_literal | float_literal | double_literal | char_literal | bool_literal )
            int alt10=8;
            switch ( input.LA(1) ) {
            case INTEGER_LITERAL:
                {
                alt10=1;
                }
                break;
            case LONG_LITERAL:
                {
                alt10=2;
                }
                break;
            case SHORT_LITERAL:
                {
                alt10=3;
                }
                break;
            case BYTE_LITERAL:
                {
                alt10=4;
                }
                break;
            case FLOAT_LITERAL:
                {
                alt10=5;
                }
                break;
            case DOUBLE_LITERAL:
                {
                alt10=6;
                }
                break;
            case CHAR_LITERAL:
                {
                alt10=7;
                }
                break;
            case BOOL_LITERAL:
                {
                alt10=8;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;
            }

            switch (alt10) {
                case 1 :
                    // org/jf/smali/smaliTreeWalker.g:364:4: integer_literal
                    {
                    pushFollow(FOLLOW_integer_literal_in_fixed_size_literal507);
                    integer_literal39=integer_literal();

                    state._fsp--;

                     value = literalTools.intToBytes(integer_literal39); 

                    }
                    break;
                case 2 :
                    // org/jf/smali/smaliTreeWalker.g:365:4: long_literal
                    {
                    pushFollow(FOLLOW_long_literal_in_fixed_size_literal514);
                    long_literal40=long_literal();

                    state._fsp--;

                     value = literalTools.longToBytes(long_literal40); 

                    }
                    break;
                case 3 :
                    // org/jf/smali/smaliTreeWalker.g:366:4: short_literal
                    {
                    pushFollow(FOLLOW_short_literal_in_fixed_size_literal521);
                    short_literal41=short_literal();

                    state._fsp--;

                     value = literalTools.shortToBytes(short_literal41); 

                    }
                    break;
                case 4 :
                    // org/jf/smali/smaliTreeWalker.g:367:4: byte_literal
                    {
                    pushFollow(FOLLOW_byte_literal_in_fixed_size_literal528);
                    byte_literal42=byte_literal();

                    state._fsp--;

                     value = new byte[] { byte_literal42 }; 

                    }
                    break;
                case 5 :
                    // org/jf/smali/smaliTreeWalker.g:368:4: float_literal
                    {
                    pushFollow(FOLLOW_float_literal_in_fixed_size_literal535);
                    float_literal43=float_literal();

                    state._fsp--;

                     value = literalTools.floatToBytes(float_literal43); 

                    }
                    break;
                case 6 :
                    // org/jf/smali/smaliTreeWalker.g:369:4: double_literal
                    {
                    pushFollow(FOLLOW_double_literal_in_fixed_size_literal542);
                    double_literal44=double_literal();

                    state._fsp--;

                     value = literalTools.doubleToBytes(double_literal44); 

                    }
                    break;
                case 7 :
                    // org/jf/smali/smaliTreeWalker.g:370:4: char_literal
                    {
                    pushFollow(FOLLOW_char_literal_in_fixed_size_literal549);
                    char_literal45=char_literal();

                    state._fsp--;

                     value = literalTools.charToBytes(char_literal45); 

                    }
                    break;
                case 8 :
                    // org/jf/smali/smaliTreeWalker.g:371:4: bool_literal
                    {
                    pushFollow(FOLLOW_bool_literal_in_fixed_size_literal556);
                    bool_literal46=bool_literal();

                    state._fsp--;

                     value = literalTools.boolToBytes(bool_literal46); 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return value;
    }
    // $ANTLR end "fixed_size_literal"


    // $ANTLR start "fixed_64bit_literal"
    // org/jf/smali/smaliTreeWalker.g:374:1: fixed_64bit_literal returns [long value] : ( integer_literal | long_literal | short_literal | byte_literal | float_literal | double_literal | char_literal | bool_literal );
    public final long fixed_64bit_literal() throws RecognitionException {
        long value = 0;

        int integer_literal47 = 0;

        long long_literal48 = 0;

        short short_literal49 = 0;

        byte byte_literal50 = 0;

        float float_literal51 = 0.0f;

        double double_literal52 = 0.0;

        char char_literal53 = 0;

        boolean bool_literal54 = false;


        try {
            // org/jf/smali/smaliTreeWalker.g:375:2: ( integer_literal | long_literal | short_literal | byte_literal | float_literal | double_literal | char_literal | bool_literal )
            int alt11=8;
            switch ( input.LA(1) ) {
            case INTEGER_LITERAL:
                {
                alt11=1;
                }
                break;
            case LONG_LITERAL:
                {
                alt11=2;
                }
                break;
            case SHORT_LITERAL:
                {
                alt11=3;
                }
                break;
            case BYTE_LITERAL:
                {
                alt11=4;
                }
                break;
            case FLOAT_LITERAL:
                {
                alt11=5;
                }
                break;
            case DOUBLE_LITERAL:
                {
                alt11=6;
                }
                break;
            case CHAR_LITERAL:
                {
                alt11=7;
                }
                break;
            case BOOL_LITERAL:
                {
                alt11=8;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;
            }

            switch (alt11) {
                case 1 :
                    // org/jf/smali/smaliTreeWalker.g:375:4: integer_literal
                    {
                    pushFollow(FOLLOW_integer_literal_in_fixed_64bit_literal571);
                    integer_literal47=integer_literal();

                    state._fsp--;

                     value = integer_literal47; 

                    }
                    break;
                case 2 :
                    // org/jf/smali/smaliTreeWalker.g:376:4: long_literal
                    {
                    pushFollow(FOLLOW_long_literal_in_fixed_64bit_literal578);
                    long_literal48=long_literal();

                    state._fsp--;

                     value = long_literal48; 

                    }
                    break;
                case 3 :
                    // org/jf/smali/smaliTreeWalker.g:377:4: short_literal
                    {
                    pushFollow(FOLLOW_short_literal_in_fixed_64bit_literal585);
                    short_literal49=short_literal();

                    state._fsp--;

                     value = short_literal49; 

                    }
                    break;
                case 4 :
                    // org/jf/smali/smaliTreeWalker.g:378:4: byte_literal
                    {
                    pushFollow(FOLLOW_byte_literal_in_fixed_64bit_literal592);
                    byte_literal50=byte_literal();

                    state._fsp--;

                     value = byte_literal50; 

                    }
                    break;
                case 5 :
                    // org/jf/smali/smaliTreeWalker.g:379:4: float_literal
                    {
                    pushFollow(FOLLOW_float_literal_in_fixed_64bit_literal599);
                    float_literal51=float_literal();

                    state._fsp--;

                     value = Float.floatToRawIntBits(float_literal51); 

                    }
                    break;
                case 6 :
                    // org/jf/smali/smaliTreeWalker.g:380:4: double_literal
                    {
                    pushFollow(FOLLOW_double_literal_in_fixed_64bit_literal606);
                    double_literal52=double_literal();

                    state._fsp--;

                     value = Double.doubleToRawLongBits(double_literal52); 

                    }
                    break;
                case 7 :
                    // org/jf/smali/smaliTreeWalker.g:381:4: char_literal
                    {
                    pushFollow(FOLLOW_char_literal_in_fixed_64bit_literal613);
                    char_literal53=char_literal();

                    state._fsp--;

                     value = char_literal53; 

                    }
                    break;
                case 8 :
                    // org/jf/smali/smaliTreeWalker.g:382:4: bool_literal
                    {
                    pushFollow(FOLLOW_bool_literal_in_fixed_64bit_literal620);
                    bool_literal54=bool_literal();

                    state._fsp--;

                     value = bool_literal54?1:0; 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return value;
    }
    // $ANTLR end "fixed_64bit_literal"


    // $ANTLR start "fixed_32bit_literal"
    // org/jf/smali/smaliTreeWalker.g:386:1: fixed_32bit_literal returns [int value] : ( integer_literal | long_literal | short_literal | byte_literal | float_literal | char_literal | bool_literal );
    public final int fixed_32bit_literal() throws RecognitionException {
        int value = 0;

        int integer_literal55 = 0;

        long long_literal56 = 0;

        short short_literal57 = 0;

        byte byte_literal58 = 0;

        float float_literal59 = 0.0f;

        char char_literal60 = 0;

        boolean bool_literal61 = false;


        try {
            // org/jf/smali/smaliTreeWalker.g:387:2: ( integer_literal | long_literal | short_literal | byte_literal | float_literal | char_literal | bool_literal )
            int alt12=7;
            switch ( input.LA(1) ) {
            case INTEGER_LITERAL:
                {
                alt12=1;
                }
                break;
            case LONG_LITERAL:
                {
                alt12=2;
                }
                break;
            case SHORT_LITERAL:
                {
                alt12=3;
                }
                break;
            case BYTE_LITERAL:
                {
                alt12=4;
                }
                break;
            case FLOAT_LITERAL:
                {
                alt12=5;
                }
                break;
            case CHAR_LITERAL:
                {
                alt12=6;
                }
                break;
            case BOOL_LITERAL:
                {
                alt12=7;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;
            }

            switch (alt12) {
                case 1 :
                    // org/jf/smali/smaliTreeWalker.g:387:4: integer_literal
                    {
                    pushFollow(FOLLOW_integer_literal_in_fixed_32bit_literal636);
                    integer_literal55=integer_literal();

                    state._fsp--;

                     value = integer_literal55; 

                    }
                    break;
                case 2 :
                    // org/jf/smali/smaliTreeWalker.g:388:4: long_literal
                    {
                    pushFollow(FOLLOW_long_literal_in_fixed_32bit_literal643);
                    long_literal56=long_literal();

                    state._fsp--;

                     literalTools.checkInt(long_literal56); value = (int)long_literal56; 

                    }
                    break;
                case 3 :
                    // org/jf/smali/smaliTreeWalker.g:389:4: short_literal
                    {
                    pushFollow(FOLLOW_short_literal_in_fixed_32bit_literal650);
                    short_literal57=short_literal();

                    state._fsp--;

                     value = short_literal57; 

                    }
                    break;
                case 4 :
                    // org/jf/smali/smaliTreeWalker.g:390:4: byte_literal
                    {
                    pushFollow(FOLLOW_byte_literal_in_fixed_32bit_literal657);
                    byte_literal58=byte_literal();

                    state._fsp--;

                     value = byte_literal58; 

                    }
                    break;
                case 5 :
                    // org/jf/smali/smaliTreeWalker.g:391:4: float_literal
                    {
                    pushFollow(FOLLOW_float_literal_in_fixed_32bit_literal664);
                    float_literal59=float_literal();

                    state._fsp--;

                     value = Float.floatToRawIntBits(float_literal59); 

                    }
                    break;
                case 6 :
                    // org/jf/smali/smaliTreeWalker.g:392:4: char_literal
                    {
                    pushFollow(FOLLOW_char_literal_in_fixed_32bit_literal671);
                    char_literal60=char_literal();

                    state._fsp--;

                     value = char_literal60; 

                    }
                    break;
                case 7 :
                    // org/jf/smali/smaliTreeWalker.g:393:4: bool_literal
                    {
                    pushFollow(FOLLOW_bool_literal_in_fixed_32bit_literal678);
                    bool_literal61=bool_literal();

                    state._fsp--;

                     value = bool_literal61?1:0; 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return value;
    }
    // $ANTLR end "fixed_32bit_literal"


    // $ANTLR start "array_elements"
    // org/jf/smali/smaliTreeWalker.g:395:1: array_elements returns [List<byte[]> values] : ^( I_ARRAY_ELEMENTS ( fixed_size_literal )* ) ;
    public final List<byte[]> array_elements() throws RecognitionException {
        List<byte[]> values = null;

        byte[] fixed_size_literal62 = null;


        try {
            // org/jf/smali/smaliTreeWalker.g:396:2: ( ^( I_ARRAY_ELEMENTS ( fixed_size_literal )* ) )
            // org/jf/smali/smaliTreeWalker.g:396:4: ^( I_ARRAY_ELEMENTS ( fixed_size_literal )* )
            {
            values = new ArrayList<byte[]>();
            match(input,I_ARRAY_ELEMENTS,FOLLOW_I_ARRAY_ELEMENTS_in_array_elements697); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // org/jf/smali/smaliTreeWalker.g:398:4: ( fixed_size_literal )*
                loop13:
                do {
                    int alt13=2;
                    switch ( input.LA(1) ) {
                    case LONG_LITERAL:
                    case SHORT_LITERAL:
                    case BYTE_LITERAL:
                    case FLOAT_LITERAL:
                    case DOUBLE_LITERAL:
                    case BOOL_LITERAL:
                    case CHAR_LITERAL:
                    case INTEGER_LITERAL:
                        {
                        alt13=1;
                        }
                        break;

                    }

                    switch (alt13) {
                	case 1 :
                	    // org/jf/smali/smaliTreeWalker.g:398:5: fixed_size_literal
                	    {
                	    pushFollow(FOLLOW_fixed_size_literal_in_array_elements703);
                	    fixed_size_literal62=fixed_size_literal();

                	    state._fsp--;


                	    				values.add(fixed_size_literal62);
                	    			

                	    }
                	    break;

                	default :
                	    break loop13;
                    }
                } while (true);


                match(input, Token.UP, null); 
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return values;
    }
    // $ANTLR end "array_elements"


    // $ANTLR start "packed_switch_target_count"
    // org/jf/smali/smaliTreeWalker.g:403:1: packed_switch_target_count returns [int targetCount] : I_PACKED_SWITCH_TARGET_COUNT ;
    public final int packed_switch_target_count() throws RecognitionException {
        int targetCount = 0;

        CommonTree I_PACKED_SWITCH_TARGET_COUNT63=null;

        try {
            // org/jf/smali/smaliTreeWalker.g:404:2: ( I_PACKED_SWITCH_TARGET_COUNT )
            // org/jf/smali/smaliTreeWalker.g:404:4: I_PACKED_SWITCH_TARGET_COUNT
            {
            I_PACKED_SWITCH_TARGET_COUNT63=(CommonTree)match(input,I_PACKED_SWITCH_TARGET_COUNT,FOLLOW_I_PACKED_SWITCH_TARGET_COUNT_in_packed_switch_target_count723); 
            targetCount = Integer.parseInt((I_PACKED_SWITCH_TARGET_COUNT63!=null?I_PACKED_SWITCH_TARGET_COUNT63.getText():null));

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return targetCount;
    }
    // $ANTLR end "packed_switch_target_count"


    // $ANTLR start "packed_switch_targets"
    // org/jf/smali/smaliTreeWalker.g:406:1: packed_switch_targets[int baseAddress] returns [int[] targets] : ^( I_PACKED_SWITCH_TARGETS packed_switch_target_count ( offset_or_label )* ) ;
    public final int[] packed_switch_targets(int baseAddress) throws RecognitionException {
        int[] targets = null;

        int packed_switch_target_count64 = 0;

        smaliTreeWalker.offset_or_label_return offset_or_label65 = null;


        try {
            // org/jf/smali/smaliTreeWalker.g:407:2: ( ^( I_PACKED_SWITCH_TARGETS packed_switch_target_count ( offset_or_label )* ) )
            // org/jf/smali/smaliTreeWalker.g:408:3: ^( I_PACKED_SWITCH_TARGETS packed_switch_target_count ( offset_or_label )* )
            {
            match(input,I_PACKED_SWITCH_TARGETS,FOLLOW_I_PACKED_SWITCH_TARGETS_in_packed_switch_targets741); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_packed_switch_target_count_in_packed_switch_targets746);
            packed_switch_target_count64=packed_switch_target_count();

            state._fsp--;


            				int targetCount = packed_switch_target_count64;
            				targets = new int[targetCount];
            				int targetsPosition = 0;
            			
            // org/jf/smali/smaliTreeWalker.g:416:4: ( offset_or_label )*
            loop14:
            do {
                int alt14=2;
                switch ( input.LA(1) ) {
                case OFFSET:
                case SIMPLE_NAME:
                    {
                    alt14=1;
                    }
                    break;

                }

                switch (alt14) {
            	case 1 :
            	    // org/jf/smali/smaliTreeWalker.g:416:5: offset_or_label
            	    {
            	    pushFollow(FOLLOW_offset_or_label_in_packed_switch_targets758);
            	    offset_or_label65=offset_or_label();

            	    state._fsp--;


            	    				targets[targetsPosition++] = (((method_scope)method_stack.peek()).currentAddress + (offset_or_label65!=null?offset_or_label65.offsetValue:0)) - baseAddress;
            	    			

            	    }
            	    break;

            	default :
            	    break loop14;
                }
            } while (true);


            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return targets;
    }
    // $ANTLR end "packed_switch_targets"


    // $ANTLR start "sparse_switch_target_count"
    // org/jf/smali/smaliTreeWalker.g:422:1: sparse_switch_target_count returns [int targetCount] : I_SPARSE_SWITCH_TARGET_COUNT ;
    public final int sparse_switch_target_count() throws RecognitionException {
        int targetCount = 0;

        CommonTree I_SPARSE_SWITCH_TARGET_COUNT66=null;

        try {
            // org/jf/smali/smaliTreeWalker.g:423:2: ( I_SPARSE_SWITCH_TARGET_COUNT )
            // org/jf/smali/smaliTreeWalker.g:423:4: I_SPARSE_SWITCH_TARGET_COUNT
            {
            I_SPARSE_SWITCH_TARGET_COUNT66=(CommonTree)match(input,I_SPARSE_SWITCH_TARGET_COUNT,FOLLOW_I_SPARSE_SWITCH_TARGET_COUNT_in_sparse_switch_target_count781); 
            targetCount = Integer.parseInt((I_SPARSE_SWITCH_TARGET_COUNT66!=null?I_SPARSE_SWITCH_TARGET_COUNT66.getText():null));

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return targetCount;
    }
    // $ANTLR end "sparse_switch_target_count"


    // $ANTLR start "sparse_switch_keys"
    // org/jf/smali/smaliTreeWalker.g:425:1: sparse_switch_keys[int targetCount] returns [int[] keys] : ^( I_SPARSE_SWITCH_KEYS ( fixed_32bit_literal )* ) ;
    public final int[] sparse_switch_keys(int targetCount) throws RecognitionException {
        int[] keys = null;

        int fixed_32bit_literal67 = 0;


        try {
            // org/jf/smali/smaliTreeWalker.g:426:2: ( ^( I_SPARSE_SWITCH_KEYS ( fixed_32bit_literal )* ) )
            // org/jf/smali/smaliTreeWalker.g:426:4: ^( I_SPARSE_SWITCH_KEYS ( fixed_32bit_literal )* )
            {

            			keys = new int[targetCount];
            			int keysPosition = 0;
            		
            match(input,I_SPARSE_SWITCH_KEYS,FOLLOW_I_SPARSE_SWITCH_KEYS_in_sparse_switch_keys801); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // org/jf/smali/smaliTreeWalker.g:431:4: ( fixed_32bit_literal )*
                loop15:
                do {
                    int alt15=2;
                    switch ( input.LA(1) ) {
                    case LONG_LITERAL:
                    case SHORT_LITERAL:
                    case BYTE_LITERAL:
                    case FLOAT_LITERAL:
                    case BOOL_LITERAL:
                    case CHAR_LITERAL:
                    case INTEGER_LITERAL:
                        {
                        alt15=1;
                        }
                        break;

                    }

                    switch (alt15) {
                	case 1 :
                	    // org/jf/smali/smaliTreeWalker.g:431:5: fixed_32bit_literal
                	    {
                	    pushFollow(FOLLOW_fixed_32bit_literal_in_sparse_switch_keys807);
                	    fixed_32bit_literal67=fixed_32bit_literal();

                	    state._fsp--;


                	    				keys[keysPosition++] = fixed_32bit_literal67;
                	    			

                	    }
                	    break;

                	default :
                	    break loop15;
                    }
                } while (true);


                match(input, Token.UP, null); 
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return keys;
    }
    // $ANTLR end "sparse_switch_keys"


    // $ANTLR start "sparse_switch_targets"
    // org/jf/smali/smaliTreeWalker.g:438:1: sparse_switch_targets[int baseAddress, int targetCount] returns [int[] targets] : ^( I_SPARSE_SWITCH_TARGETS ( offset_or_label )* ) ;
    public final int[] sparse_switch_targets(int baseAddress, int targetCount) throws RecognitionException {
        int[] targets = null;

        smaliTreeWalker.offset_or_label_return offset_or_label68 = null;


        try {
            // org/jf/smali/smaliTreeWalker.g:439:2: ( ^( I_SPARSE_SWITCH_TARGETS ( offset_or_label )* ) )
            // org/jf/smali/smaliTreeWalker.g:439:4: ^( I_SPARSE_SWITCH_TARGETS ( offset_or_label )* )
            {

            			targets = new int[targetCount];
            			int targetsPosition = 0;
            		
            match(input,I_SPARSE_SWITCH_TARGETS,FOLLOW_I_SPARSE_SWITCH_TARGETS_in_sparse_switch_targets837); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // org/jf/smali/smaliTreeWalker.g:444:4: ( offset_or_label )*
                loop16:
                do {
                    int alt16=2;
                    switch ( input.LA(1) ) {
                    case OFFSET:
                    case SIMPLE_NAME:
                        {
                        alt16=1;
                        }
                        break;

                    }

                    switch (alt16) {
                	case 1 :
                	    // org/jf/smali/smaliTreeWalker.g:444:5: offset_or_label
                	    {
                	    pushFollow(FOLLOW_offset_or_label_in_sparse_switch_targets843);
                	    offset_or_label68=offset_or_label();

                	    state._fsp--;


                	    				targets[targetsPosition++] = (((method_scope)method_stack.peek()).currentAddress + (offset_or_label68!=null?offset_or_label68.offsetValue:0)) - baseAddress;
                	    			

                	    }
                	    break;

                	default :
                	    break loop16;
                    }
                } while (true);


                match(input, Token.UP, null); 
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return targets;
    }
    // $ANTLR end "sparse_switch_targets"

    protected static class method_scope {
        HashMap<String, Integer> labels;
        TryListBuilder tryList;
        int currentAddress;
        DebugInfoBuilder debugInfo;
        HashMap<Integer, Integer> packedSwitchDeclarations;
        HashMap<Integer, Integer> sparseSwitchDeclarations;
    }
    protected Stack method_stack = new Stack();

    public static class method_return extends TreeRuleReturnScope {
        public ClassDataItem.EncodedMethod encodedMethod;
        public AnnotationSetItem methodAnnotationSet;
        public AnnotationSetRefList parameterAnnotationSets;
    };

    // $ANTLR start "method"
    // org/jf/smali/smaliTreeWalker.g:450:1: method returns [\tClassDataItem.EncodedMethod encodedMethod,\n\t\tAnnotationSetItem methodAnnotationSet,\n\t\tAnnotationSetRefList parameterAnnotationSets] : ^( I_METHOD method_name_and_prototype access_list ( registers_directive )? labels packed_switch_declarations sparse_switch_declarations statements[totalMethodRegisters, methodParameterRegisters] catches parameters ordered_debug_directives[totalMethodRegisters, methodParameterRegisters] annotations ) ;
    public final smaliTreeWalker.method_return method() throws RecognitionException {
        method_stack.push(new method_scope());
        smaliTreeWalker.method_return retval = new smaliTreeWalker.method_return();
        retval.start = input.LT(1);

        CommonTree I_METHOD73=null;
        MethodIdItem method_name_and_prototype69 = null;

        int access_list70 = 0;

        smaliTreeWalker.registers_directive_return registers_directive71 = null;

        smaliTreeWalker.statements_return statements72 = null;

        AnnotationSetItem annotations74 = null;

        AnnotationSetRefList parameters75 = null;



        		MethodIdItem methodIdItem = null;
        		int totalMethodRegisters = 0;
        		int methodParameterRegisters = 0;
        		int accessFlags = 0;
        		boolean isStatic = false;
        	
        try {
            // org/jf/smali/smaliTreeWalker.g:470:2: ( ^( I_METHOD method_name_and_prototype access_list ( registers_directive )? labels packed_switch_declarations sparse_switch_declarations statements[totalMethodRegisters, methodParameterRegisters] catches parameters ordered_debug_directives[totalMethodRegisters, methodParameterRegisters] annotations ) )
            // org/jf/smali/smaliTreeWalker.g:470:4: ^( I_METHOD method_name_and_prototype access_list ( registers_directive )? labels packed_switch_declarations sparse_switch_declarations statements[totalMethodRegisters, methodParameterRegisters] catches parameters ordered_debug_directives[totalMethodRegisters, methodParameterRegisters] annotations )
            {

            			((method_scope)method_stack.peek()).labels = new HashMap<String, Integer>();
            			((method_scope)method_stack.peek()).tryList = new TryListBuilder();
            			((method_scope)method_stack.peek()).currentAddress = 0;
            			((method_scope)method_stack.peek()).debugInfo = new DebugInfoBuilder();
            			((method_scope)method_stack.peek()).packedSwitchDeclarations = new HashMap<Integer, Integer>();
            			((method_scope)method_stack.peek()).sparseSwitchDeclarations = new HashMap<Integer, Integer>();
            		
            I_METHOD73=(CommonTree)match(input,I_METHOD,FOLLOW_I_METHOD_in_method885); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_method_name_and_prototype_in_method890);
            method_name_and_prototype69=method_name_and_prototype();

            state._fsp--;

            pushFollow(FOLLOW_access_list_in_method895);
            access_list70=access_list();

            state._fsp--;


            				methodIdItem = method_name_and_prototype69;
            				accessFlags = access_list70;
            				isStatic = (accessFlags & AccessFlags.STATIC.getValue()) != 0;
            				methodParameterRegisters = methodIdItem.getPrototype().getParameterRegisterCount();
            				if (!isStatic) {
            					methodParameterRegisters++;
            				}
            			
            // org/jf/smali/smaliTreeWalker.g:490:4: ( registers_directive )?
            int alt17=2;
            switch ( input.LA(1) ) {
                case I_REGISTERS:
                case I_LOCALS:
                    {
                    alt17=1;
                    }
                    break;
            }

            switch (alt17) {
                case 1 :
                    // org/jf/smali/smaliTreeWalker.g:490:6: registers_directive
                    {
                    pushFollow(FOLLOW_registers_directive_in_method907);
                    registers_directive71=registers_directive();

                    state._fsp--;


                    					if ((registers_directive71!=null?registers_directive71.isLocalsDirective:false)) {
                    						totalMethodRegisters = (registers_directive71!=null?registers_directive71.registers:0) + methodParameterRegisters;
                    					} else {
                    						totalMethodRegisters = (registers_directive71!=null?registers_directive71.registers:0);
                    					}
                    				

                    }
                    break;

            }

            pushFollow(FOLLOW_labels_in_method924);
            labels();

            state._fsp--;

            pushFollow(FOLLOW_packed_switch_declarations_in_method929);
            packed_switch_declarations();

            state._fsp--;

            pushFollow(FOLLOW_sparse_switch_declarations_in_method934);
            sparse_switch_declarations();

            state._fsp--;

            pushFollow(FOLLOW_statements_in_method939);
            statements72=statements(totalMethodRegisters, methodParameterRegisters);

            state._fsp--;

            pushFollow(FOLLOW_catches_in_method945);
            catches();

            state._fsp--;

            pushFollow(FOLLOW_parameters_in_method950);
            parameters75=parameters();

            state._fsp--;

            pushFollow(FOLLOW_ordered_debug_directives_in_method955);
            ordered_debug_directives(totalMethodRegisters, methodParameterRegisters);

            state._fsp--;

            pushFollow(FOLLOW_annotations_in_method961);
            annotations74=annotations();

            state._fsp--;


            match(input, Token.UP, null); 

            		Pair<List<CodeItem.TryItem>, List<CodeItem.EncodedCatchHandler>> temp = ((method_scope)method_stack.peek()).tryList.encodeTries();
            		List<CodeItem.TryItem> tries = temp.first;
            		List<CodeItem.EncodedCatchHandler> handlers = temp.second;

            		DebugInfoItem debugInfoItem = ((method_scope)method_stack.peek()).debugInfo.encodeDebugInfo(dexFile);

            		CodeItem codeItem;

            		boolean isAbstract = false;
            		boolean isNative = false;

            		if ((accessFlags & AccessFlags.ABSTRACT.getValue()) != 0) {
            			isAbstract = true;
            		} else if ((accessFlags & AccessFlags.NATIVE.getValue()) != 0) {
            			isNative = true;
            		}

            		if ((statements72!=null?statements72.instructions:null).size() == 0) {
            			if (!isAbstract && !isNative) {
            				throw new SemanticException(input, I_METHOD73, "A non-abstract/non-native method must have at least 1 instruction");
            			}

            			String methodType;
            			if (isAbstract) {
            				methodType = "an abstract";
            			} else {
            				methodType = "a native";
            			}

            		    	if ((registers_directive71!=null?((CommonTree)registers_directive71.start):null) != null) {
            		    		if ((registers_directive71!=null?registers_directive71.isLocalsDirective:false)) {
            			    		throw new SemanticException(input, (registers_directive71!=null?((CommonTree)registers_directive71.start):null), "A .locals directive is not valid in %s method", methodType);
            		    		} else {
            		    			throw new SemanticException(input, (registers_directive71!=null?((CommonTree)registers_directive71.start):null), "A  .registers directive is not valid in %s method", methodType);
            		    		}
            		    	}

            		    	if (((method_scope)method_stack.peek()).labels.size() > 0) {
            		    		throw new SemanticException(input, I_METHOD73, "Labels cannot be present in %s method", methodType);
            		    	}

            		    	if ((tries != null && tries.size() > 0) || (handlers != null && handlers.size() > 0)) {
            		    		throw new SemanticException(input, I_METHOD73, "try/catch blocks cannot be present in %s method", methodType);
            		    	}

            		    	if (debugInfoItem != null) {
            		    		throw new SemanticException(input, I_METHOD73, "debug directives cannot be present in %s method", methodType);
            		    	}

            		    	codeItem = null;
            		} else {
            			if (isAbstract) {
            				throw new SemanticException(input, I_METHOD73, "An abstract method cannot have any instructions");
            			}
            			if (isNative) {
            				throw new SemanticException(input, I_METHOD73, "A native method cannot have any instructions");
            			}

            			if ((registers_directive71!=null?((CommonTree)registers_directive71.start):null) == null) {
            				throw new SemanticException(input, I_METHOD73, "A .registers or .locals directive must be present for a non-abstract/non-final method");
            			}

            			if (totalMethodRegisters < methodParameterRegisters) {
            				throw new SemanticException(input, (registers_directive71!=null?((CommonTree)registers_directive71.start):null), "This method requires at least " +
            								Integer.toString(methodParameterRegisters) +
            								" registers, for the method parameters");
            			}

            			int methodParameterCount = methodIdItem.getPrototype().getParameterRegisterCount();
            			if (((method_scope)method_stack.peek()).debugInfo.getParameterNameCount() > methodParameterCount) {
            				throw new SemanticException(input, I_METHOD73, "Too many parameter names specified. This method only has " +
            								Integer.toString(methodParameterCount) +
            								" parameters.");
            			}

            			codeItem = CodeItem.internCodeItem(dexFile,
            						totalMethodRegisters,
            						methodParameterRegisters,
            						(statements72!=null?statements72.maxOutRegisters:0),
            						debugInfoItem,
            						(statements72!=null?statements72.instructions:null),
            						tries,
            						handlers);
            		}

            		retval.encodedMethod = new ClassDataItem.EncodedMethod(methodIdItem, accessFlags, codeItem);

            		if (annotations74 != null) {
            			retval.methodAnnotationSet = annotations74;
            		}

            		if (parameters75 != null) {
            			retval.parameterAnnotationSets = parameters75;
            		}
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            method_stack.pop();
        }
        return retval;
    }
    // $ANTLR end "method"


    // $ANTLR start "method_prototype"
    // org/jf/smali/smaliTreeWalker.g:605:1: method_prototype returns [ProtoIdItem protoIdItem] : ^( I_METHOD_PROTOTYPE ^( I_METHOD_RETURN_TYPE type_descriptor ) field_type_list ) ;
    public final ProtoIdItem method_prototype() throws RecognitionException {
        ProtoIdItem protoIdItem = null;

        TypeIdItem type_descriptor76 = null;

        List<TypeIdItem> field_type_list77 = null;


        try {
            // org/jf/smali/smaliTreeWalker.g:606:2: ( ^( I_METHOD_PROTOTYPE ^( I_METHOD_RETURN_TYPE type_descriptor ) field_type_list ) )
            // org/jf/smali/smaliTreeWalker.g:606:4: ^( I_METHOD_PROTOTYPE ^( I_METHOD_RETURN_TYPE type_descriptor ) field_type_list )
            {
            match(input,I_METHOD_PROTOTYPE,FOLLOW_I_METHOD_PROTOTYPE_in_method_prototype981); 

            match(input, Token.DOWN, null); 
            match(input,I_METHOD_RETURN_TYPE,FOLLOW_I_METHOD_RETURN_TYPE_in_method_prototype984); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_type_descriptor_in_method_prototype986);
            type_descriptor76=type_descriptor();

            state._fsp--;


            match(input, Token.UP, null); 
            pushFollow(FOLLOW_field_type_list_in_method_prototype989);
            field_type_list77=field_type_list();

            state._fsp--;


            match(input, Token.UP, null); 

            		TypeIdItem returnType = type_descriptor76;
            		List<TypeIdItem> parameterTypes = field_type_list77;
            		TypeListItem parameterTypeListItem = null;
            		if (parameterTypes != null && parameterTypes.size() > 0) {
            			parameterTypeListItem = TypeListItem.internTypeListItem(dexFile, parameterTypes);
            		}

            		protoIdItem = ProtoIdItem.internProtoIdItem(dexFile, returnType, parameterTypeListItem);
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return protoIdItem;
    }
    // $ANTLR end "method_prototype"


    // $ANTLR start "method_name_and_prototype"
    // org/jf/smali/smaliTreeWalker.g:618:1: method_name_and_prototype returns [MethodIdItem methodIdItem] : SIMPLE_NAME method_prototype ;
    public final MethodIdItem method_name_and_prototype() throws RecognitionException {
        MethodIdItem methodIdItem = null;

        CommonTree SIMPLE_NAME78=null;
        ProtoIdItem method_prototype79 = null;


        try {
            // org/jf/smali/smaliTreeWalker.g:619:2: ( SIMPLE_NAME method_prototype )
            // org/jf/smali/smaliTreeWalker.g:619:4: SIMPLE_NAME method_prototype
            {
            SIMPLE_NAME78=(CommonTree)match(input,SIMPLE_NAME,FOLLOW_SIMPLE_NAME_in_method_name_and_prototype1005); 
            pushFollow(FOLLOW_method_prototype_in_method_name_and_prototype1007);
            method_prototype79=method_prototype();

            state._fsp--;


            		String methodNameString = (SIMPLE_NAME78!=null?SIMPLE_NAME78.getText():null);
            		StringIdItem methodName = StringIdItem.internStringIdItem(dexFile, methodNameString);
            		ProtoIdItem protoIdItem = method_prototype79;

            		methodIdItem = MethodIdItem.internMethodIdItem(dexFile, classType, protoIdItem, methodName);
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return methodIdItem;
    }
    // $ANTLR end "method_name_and_prototype"


    // $ANTLR start "field_type_list"
    // org/jf/smali/smaliTreeWalker.g:628:1: field_type_list returns [List<TypeIdItem> types] : ( nonvoid_type_descriptor )* ;
    public final List<TypeIdItem> field_type_list() throws RecognitionException {
        List<TypeIdItem> types = null;

        smaliTreeWalker.nonvoid_type_descriptor_return nonvoid_type_descriptor80 = null;



        		types = new LinkedList<TypeIdItem>();
        	
        try {
            // org/jf/smali/smaliTreeWalker.g:633:2: ( ( nonvoid_type_descriptor )* )
            // org/jf/smali/smaliTreeWalker.g:633:4: ( nonvoid_type_descriptor )*
            {
            // org/jf/smali/smaliTreeWalker.g:633:4: ( nonvoid_type_descriptor )*
            loop18:
            do {
                int alt18=2;
                switch ( input.LA(1) ) {
                case PRIMITIVE_TYPE:
                case CLASS_DESCRIPTOR:
                case ARRAY_DESCRIPTOR:
                    {
                    alt18=1;
                    }
                    break;

                }

                switch (alt18) {
            	case 1 :
            	    // org/jf/smali/smaliTreeWalker.g:634:4: nonvoid_type_descriptor
            	    {
            	    pushFollow(FOLLOW_nonvoid_type_descriptor_in_field_type_list1034);
            	    nonvoid_type_descriptor80=nonvoid_type_descriptor();

            	    state._fsp--;


            	    				types.add((nonvoid_type_descriptor80!=null?nonvoid_type_descriptor80.type:null));
            	    			

            	    }
            	    break;

            	default :
            	    break loop18;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return types;
    }
    // $ANTLR end "field_type_list"


    // $ANTLR start "fully_qualified_method"
    // org/jf/smali/smaliTreeWalker.g:641:1: fully_qualified_method returns [MethodIdItem methodIdItem] : reference_type_descriptor SIMPLE_NAME method_prototype ;
    public final MethodIdItem fully_qualified_method() throws RecognitionException {
        MethodIdItem methodIdItem = null;

        CommonTree SIMPLE_NAME82=null;
        smaliTreeWalker.reference_type_descriptor_return reference_type_descriptor81 = null;

        ProtoIdItem method_prototype83 = null;


        try {
            // org/jf/smali/smaliTreeWalker.g:642:2: ( reference_type_descriptor SIMPLE_NAME method_prototype )
            // org/jf/smali/smaliTreeWalker.g:642:4: reference_type_descriptor SIMPLE_NAME method_prototype
            {
            pushFollow(FOLLOW_reference_type_descriptor_in_fully_qualified_method1057);
            reference_type_descriptor81=reference_type_descriptor();

            state._fsp--;

            SIMPLE_NAME82=(CommonTree)match(input,SIMPLE_NAME,FOLLOW_SIMPLE_NAME_in_fully_qualified_method1059); 
            pushFollow(FOLLOW_method_prototype_in_fully_qualified_method1061);
            method_prototype83=method_prototype();

            state._fsp--;


            		TypeIdItem classType = (reference_type_descriptor81!=null?reference_type_descriptor81.type:null);
            		StringIdItem methodName = StringIdItem.internStringIdItem(dexFile, (SIMPLE_NAME82!=null?SIMPLE_NAME82.getText():null));
            		ProtoIdItem prototype = method_prototype83;
            		methodIdItem = MethodIdItem.internMethodIdItem(dexFile, classType, prototype, methodName);
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return methodIdItem;
    }
    // $ANTLR end "fully_qualified_method"


    // $ANTLR start "fully_qualified_field"
    // org/jf/smali/smaliTreeWalker.g:650:1: fully_qualified_field returns [FieldIdItem fieldIdItem] : reference_type_descriptor SIMPLE_NAME nonvoid_type_descriptor ;
    public final FieldIdItem fully_qualified_field() throws RecognitionException {
        FieldIdItem fieldIdItem = null;

        CommonTree SIMPLE_NAME85=null;
        smaliTreeWalker.reference_type_descriptor_return reference_type_descriptor84 = null;

        smaliTreeWalker.nonvoid_type_descriptor_return nonvoid_type_descriptor86 = null;


        try {
            // org/jf/smali/smaliTreeWalker.g:651:2: ( reference_type_descriptor SIMPLE_NAME nonvoid_type_descriptor )
            // org/jf/smali/smaliTreeWalker.g:651:4: reference_type_descriptor SIMPLE_NAME nonvoid_type_descriptor
            {
            pushFollow(FOLLOW_reference_type_descriptor_in_fully_qualified_field1076);
            reference_type_descriptor84=reference_type_descriptor();

            state._fsp--;

            SIMPLE_NAME85=(CommonTree)match(input,SIMPLE_NAME,FOLLOW_SIMPLE_NAME_in_fully_qualified_field1078); 
            pushFollow(FOLLOW_nonvoid_type_descriptor_in_fully_qualified_field1080);
            nonvoid_type_descriptor86=nonvoid_type_descriptor();

            state._fsp--;


            		TypeIdItem classType = (reference_type_descriptor84!=null?reference_type_descriptor84.type:null);
            		StringIdItem fieldName = StringIdItem.internStringIdItem(dexFile, (SIMPLE_NAME85!=null?SIMPLE_NAME85.getText():null));
            		TypeIdItem fieldType = (nonvoid_type_descriptor86!=null?nonvoid_type_descriptor86.type:null);
            		fieldIdItem = FieldIdItem.internFieldIdItem(dexFile, classType, fieldType, fieldName);
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return fieldIdItem;
    }
    // $ANTLR end "fully_qualified_field"

    public static class registers_directive_return extends TreeRuleReturnScope {
        public boolean isLocalsDirective;
        public int registers;
    };

    // $ANTLR start "registers_directive"
    // org/jf/smali/smaliTreeWalker.g:659:1: registers_directive returns [boolean isLocalsDirective, int registers] : ^( ( I_REGISTERS | I_LOCALS ) short_integral_literal ) ;
    public final smaliTreeWalker.registers_directive_return registers_directive() throws RecognitionException {
        smaliTreeWalker.registers_directive_return retval = new smaliTreeWalker.registers_directive_return();
        retval.start = input.LT(1);

        short short_integral_literal87 = 0;


        try {
            // org/jf/smali/smaliTreeWalker.g:660:2: ( ^( ( I_REGISTERS | I_LOCALS ) short_integral_literal ) )
            // org/jf/smali/smaliTreeWalker.g:660:4: ^( ( I_REGISTERS | I_LOCALS ) short_integral_literal )
            {
            retval.registers = 0;
            // org/jf/smali/smaliTreeWalker.g:661:6: ( I_REGISTERS | I_LOCALS )
            int alt19=2;
            switch ( input.LA(1) ) {
            case I_REGISTERS:
                {
                alt19=1;
                }
                break;
            case I_LOCALS:
                {
                alt19=2;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 19, 0, input);

                throw nvae;
            }

            switch (alt19) {
                case 1 :
                    // org/jf/smali/smaliTreeWalker.g:661:8: I_REGISTERS
                    {
                    match(input,I_REGISTERS,FOLLOW_I_REGISTERS_in_registers_directive1103); 
                    retval.isLocalsDirective = false;

                    }
                    break;
                case 2 :
                    // org/jf/smali/smaliTreeWalker.g:662:6: I_LOCALS
                    {
                    match(input,I_LOCALS,FOLLOW_I_LOCALS_in_registers_directive1112); 
                    retval.isLocalsDirective = true;

                    }
                    break;

            }


            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_short_integral_literal_in_registers_directive1124);
            short_integral_literal87=short_integral_literal();

            state._fsp--;

            retval.registers = short_integral_literal87;

            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "registers_directive"


    // $ANTLR start "labels"
    // org/jf/smali/smaliTreeWalker.g:667:1: labels : ^( I_LABELS ( label_def )* ) ;
    public final void labels() throws RecognitionException {
        try {
            // org/jf/smali/smaliTreeWalker.g:668:2: ( ^( I_LABELS ( label_def )* ) )
            // org/jf/smali/smaliTreeWalker.g:668:4: ^( I_LABELS ( label_def )* )
            {
            match(input,I_LABELS,FOLLOW_I_LABELS_in_labels1140); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // org/jf/smali/smaliTreeWalker.g:668:15: ( label_def )*
                loop20:
                do {
                    int alt20=2;
                    switch ( input.LA(1) ) {
                    case I_LABEL:
                        {
                        alt20=1;
                        }
                        break;

                    }

                    switch (alt20) {
                	case 1 :
                	    // org/jf/smali/smaliTreeWalker.g:668:15: label_def
                	    {
                	    pushFollow(FOLLOW_label_def_in_labels1142);
                	    label_def();

                	    state._fsp--;


                	    }
                	    break;

                	default :
                	    break loop20;
                    }
                } while (true);


                match(input, Token.UP, null); 
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "labels"


    // $ANTLR start "label_def"
    // org/jf/smali/smaliTreeWalker.g:670:1: label_def : ^( I_LABEL SIMPLE_NAME address ) ;
    public final void label_def() throws RecognitionException {
        CommonTree SIMPLE_NAME88=null;
        CommonTree I_LABEL89=null;
        int address90 = 0;


        try {
            // org/jf/smali/smaliTreeWalker.g:671:2: ( ^( I_LABEL SIMPLE_NAME address ) )
            // org/jf/smali/smaliTreeWalker.g:671:4: ^( I_LABEL SIMPLE_NAME address )
            {
            I_LABEL89=(CommonTree)match(input,I_LABEL,FOLLOW_I_LABEL_in_label_def1154); 

            match(input, Token.DOWN, null); 
            SIMPLE_NAME88=(CommonTree)match(input,SIMPLE_NAME,FOLLOW_SIMPLE_NAME_in_label_def1156); 
            pushFollow(FOLLOW_address_in_label_def1158);
            address90=address();

            state._fsp--;


            match(input, Token.UP, null); 

            			if (((method_scope)method_stack.peek()).labels.containsKey((SIMPLE_NAME88!=null?SIMPLE_NAME88.getText():null))) {
            				throw new SemanticException(input, I_LABEL89, "Label " + (SIMPLE_NAME88!=null?SIMPLE_NAME88.getText():null) + " has multiple defintions.");
            			}


            			((method_scope)method_stack.peek()).labels.put((SIMPLE_NAME88!=null?SIMPLE_NAME88.getText():null), address90);
            		

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "label_def"


    // $ANTLR start "packed_switch_declarations"
    // org/jf/smali/smaliTreeWalker.g:681:1: packed_switch_declarations : ^( I_PACKED_SWITCH_DECLARATIONS ( packed_switch_declaration )* ) ;
    public final void packed_switch_declarations() throws RecognitionException {
        try {
            // org/jf/smali/smaliTreeWalker.g:682:2: ( ^( I_PACKED_SWITCH_DECLARATIONS ( packed_switch_declaration )* ) )
            // org/jf/smali/smaliTreeWalker.g:682:4: ^( I_PACKED_SWITCH_DECLARATIONS ( packed_switch_declaration )* )
            {
            match(input,I_PACKED_SWITCH_DECLARATIONS,FOLLOW_I_PACKED_SWITCH_DECLARATIONS_in_packed_switch_declarations1173); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // org/jf/smali/smaliTreeWalker.g:682:35: ( packed_switch_declaration )*
                loop21:
                do {
                    int alt21=2;
                    switch ( input.LA(1) ) {
                    case I_PACKED_SWITCH_DECLARATION:
                        {
                        alt21=1;
                        }
                        break;

                    }

                    switch (alt21) {
                	case 1 :
                	    // org/jf/smali/smaliTreeWalker.g:682:35: packed_switch_declaration
                	    {
                	    pushFollow(FOLLOW_packed_switch_declaration_in_packed_switch_declarations1175);
                	    packed_switch_declaration();

                	    state._fsp--;


                	    }
                	    break;

                	default :
                	    break loop21;
                    }
                } while (true);


                match(input, Token.UP, null); 
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "packed_switch_declarations"


    // $ANTLR start "packed_switch_declaration"
    // org/jf/smali/smaliTreeWalker.g:683:1: packed_switch_declaration : ^( I_PACKED_SWITCH_DECLARATION address offset_or_label_absolute[$address.address] ) ;
    public final void packed_switch_declaration() throws RecognitionException {
        int address91 = 0;

        int offset_or_label_absolute92 = 0;


        try {
            // org/jf/smali/smaliTreeWalker.g:684:2: ( ^( I_PACKED_SWITCH_DECLARATION address offset_or_label_absolute[$address.address] ) )
            // org/jf/smali/smaliTreeWalker.g:684:4: ^( I_PACKED_SWITCH_DECLARATION address offset_or_label_absolute[$address.address] )
            {
            match(input,I_PACKED_SWITCH_DECLARATION,FOLLOW_I_PACKED_SWITCH_DECLARATION_in_packed_switch_declaration1186); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_address_in_packed_switch_declaration1188);
            address91=address();

            state._fsp--;

            pushFollow(FOLLOW_offset_or_label_absolute_in_packed_switch_declaration1190);
            offset_or_label_absolute92=offset_or_label_absolute(address91);

            state._fsp--;


            match(input, Token.UP, null); 

            			int switchDataAddress = offset_or_label_absolute92;
            			if ((switchDataAddress % 2) != 0) {
            				switchDataAddress++;
            			}
            			if (!((method_scope)method_stack.peek()).packedSwitchDeclarations.containsKey(switchDataAddress)) {
            				((method_scope)method_stack.peek()).packedSwitchDeclarations.put(switchDataAddress, address91);
            			}
            		

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "packed_switch_declaration"


    // $ANTLR start "sparse_switch_declarations"
    // org/jf/smali/smaliTreeWalker.g:695:1: sparse_switch_declarations : ^( I_SPARSE_SWITCH_DECLARATIONS ( sparse_switch_declaration )* ) ;
    public final void sparse_switch_declarations() throws RecognitionException {
        try {
            // org/jf/smali/smaliTreeWalker.g:696:2: ( ^( I_SPARSE_SWITCH_DECLARATIONS ( sparse_switch_declaration )* ) )
            // org/jf/smali/smaliTreeWalker.g:696:4: ^( I_SPARSE_SWITCH_DECLARATIONS ( sparse_switch_declaration )* )
            {
            match(input,I_SPARSE_SWITCH_DECLARATIONS,FOLLOW_I_SPARSE_SWITCH_DECLARATIONS_in_sparse_switch_declarations1206); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // org/jf/smali/smaliTreeWalker.g:696:35: ( sparse_switch_declaration )*
                loop22:
                do {
                    int alt22=2;
                    switch ( input.LA(1) ) {
                    case I_SPARSE_SWITCH_DECLARATION:
                        {
                        alt22=1;
                        }
                        break;

                    }

                    switch (alt22) {
                	case 1 :
                	    // org/jf/smali/smaliTreeWalker.g:696:35: sparse_switch_declaration
                	    {
                	    pushFollow(FOLLOW_sparse_switch_declaration_in_sparse_switch_declarations1208);
                	    sparse_switch_declaration();

                	    state._fsp--;


                	    }
                	    break;

                	default :
                	    break loop22;
                    }
                } while (true);


                match(input, Token.UP, null); 
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "sparse_switch_declarations"


    // $ANTLR start "sparse_switch_declaration"
    // org/jf/smali/smaliTreeWalker.g:697:1: sparse_switch_declaration : ^( I_SPARSE_SWITCH_DECLARATION address offset_or_label_absolute[$address.address] ) ;
    public final void sparse_switch_declaration() throws RecognitionException {
        int address93 = 0;

        int offset_or_label_absolute94 = 0;


        try {
            // org/jf/smali/smaliTreeWalker.g:698:2: ( ^( I_SPARSE_SWITCH_DECLARATION address offset_or_label_absolute[$address.address] ) )
            // org/jf/smali/smaliTreeWalker.g:698:4: ^( I_SPARSE_SWITCH_DECLARATION address offset_or_label_absolute[$address.address] )
            {
            match(input,I_SPARSE_SWITCH_DECLARATION,FOLLOW_I_SPARSE_SWITCH_DECLARATION_in_sparse_switch_declaration1219); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_address_in_sparse_switch_declaration1221);
            address93=address();

            state._fsp--;

            pushFollow(FOLLOW_offset_or_label_absolute_in_sparse_switch_declaration1223);
            offset_or_label_absolute94=offset_or_label_absolute(address93);

            state._fsp--;


            match(input, Token.UP, null); 

            			int switchDataAddress = offset_or_label_absolute94;
            			if ((switchDataAddress % 2) != 0) {
            				switchDataAddress++;
            			}
            			if (!((method_scope)method_stack.peek()).sparseSwitchDeclarations.containsKey(switchDataAddress)) {
            				((method_scope)method_stack.peek()).sparseSwitchDeclarations.put(switchDataAddress, address93);
            			}

            		

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "sparse_switch_declaration"


    // $ANTLR start "catches"
    // org/jf/smali/smaliTreeWalker.g:710:1: catches : ^( I_CATCHES ( catch_directive )* ( catchall_directive )* ) ;
    public final void catches() throws RecognitionException {
        try {
            // org/jf/smali/smaliTreeWalker.g:710:9: ( ^( I_CATCHES ( catch_directive )* ( catchall_directive )* ) )
            // org/jf/smali/smaliTreeWalker.g:710:11: ^( I_CATCHES ( catch_directive )* ( catchall_directive )* )
            {
            match(input,I_CATCHES,FOLLOW_I_CATCHES_in_catches1238); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // org/jf/smali/smaliTreeWalker.g:710:23: ( catch_directive )*
                loop23:
                do {
                    int alt23=2;
                    switch ( input.LA(1) ) {
                    case I_CATCH:
                        {
                        alt23=1;
                        }
                        break;

                    }

                    switch (alt23) {
                	case 1 :
                	    // org/jf/smali/smaliTreeWalker.g:710:23: catch_directive
                	    {
                	    pushFollow(FOLLOW_catch_directive_in_catches1240);
                	    catch_directive();

                	    state._fsp--;


                	    }
                	    break;

                	default :
                	    break loop23;
                    }
                } while (true);

                // org/jf/smali/smaliTreeWalker.g:710:40: ( catchall_directive )*
                loop24:
                do {
                    int alt24=2;
                    switch ( input.LA(1) ) {
                    case I_CATCHALL:
                        {
                        alt24=1;
                        }
                        break;

                    }

                    switch (alt24) {
                	case 1 :
                	    // org/jf/smali/smaliTreeWalker.g:710:40: catchall_directive
                	    {
                	    pushFollow(FOLLOW_catchall_directive_in_catches1243);
                	    catchall_directive();

                	    state._fsp--;


                	    }
                	    break;

                	default :
                	    break loop24;
                    }
                } while (true);


                match(input, Token.UP, null); 
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "catches"


    // $ANTLR start "catch_directive"
    // org/jf/smali/smaliTreeWalker.g:712:1: catch_directive : ^( I_CATCH address nonvoid_type_descriptor from= offset_or_label_absolute[$address.address] to= offset_or_label_absolute[$address.address] using= offset_or_label_absolute[$address.address] ) ;
    public final void catch_directive() throws RecognitionException {
        int from = 0;

        int to = 0;

        int using = 0;

        int address95 = 0;

        smaliTreeWalker.nonvoid_type_descriptor_return nonvoid_type_descriptor96 = null;


        try {
            // org/jf/smali/smaliTreeWalker.g:713:2: ( ^( I_CATCH address nonvoid_type_descriptor from= offset_or_label_absolute[$address.address] to= offset_or_label_absolute[$address.address] using= offset_or_label_absolute[$address.address] ) )
            // org/jf/smali/smaliTreeWalker.g:713:4: ^( I_CATCH address nonvoid_type_descriptor from= offset_or_label_absolute[$address.address] to= offset_or_label_absolute[$address.address] using= offset_or_label_absolute[$address.address] )
            {
            match(input,I_CATCH,FOLLOW_I_CATCH_in_catch_directive1255); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_address_in_catch_directive1257);
            address95=address();

            state._fsp--;

            pushFollow(FOLLOW_nonvoid_type_descriptor_in_catch_directive1259);
            nonvoid_type_descriptor96=nonvoid_type_descriptor();

            state._fsp--;

            pushFollow(FOLLOW_offset_or_label_absolute_in_catch_directive1263);
            from=offset_or_label_absolute(address95);

            state._fsp--;

            pushFollow(FOLLOW_offset_or_label_absolute_in_catch_directive1268);
            to=offset_or_label_absolute(address95);

            state._fsp--;

            pushFollow(FOLLOW_offset_or_label_absolute_in_catch_directive1277);
            using=offset_or_label_absolute(address95);

            state._fsp--;


            match(input, Token.UP, null); 

            			TypeIdItem type = (nonvoid_type_descriptor96!=null?nonvoid_type_descriptor96.type:null);
            			int startAddress = from;
            			int endAddress = to;
            			int handlerAddress = using;

            			((method_scope)method_stack.peek()).tryList.addHandler(type, startAddress, endAddress, handlerAddress);
            		

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "catch_directive"


    // $ANTLR start "catchall_directive"
    // org/jf/smali/smaliTreeWalker.g:724:1: catchall_directive : ^( I_CATCHALL address from= offset_or_label_absolute[$address.address] to= offset_or_label_absolute[$address.address] using= offset_or_label_absolute[$address.address] ) ;
    public final void catchall_directive() throws RecognitionException {
        int from = 0;

        int to = 0;

        int using = 0;

        int address97 = 0;


        try {
            // org/jf/smali/smaliTreeWalker.g:725:2: ( ^( I_CATCHALL address from= offset_or_label_absolute[$address.address] to= offset_or_label_absolute[$address.address] using= offset_or_label_absolute[$address.address] ) )
            // org/jf/smali/smaliTreeWalker.g:725:4: ^( I_CATCHALL address from= offset_or_label_absolute[$address.address] to= offset_or_label_absolute[$address.address] using= offset_or_label_absolute[$address.address] )
            {
            match(input,I_CATCHALL,FOLLOW_I_CATCHALL_in_catchall_directive1293); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_address_in_catchall_directive1295);
            address97=address();

            state._fsp--;

            pushFollow(FOLLOW_offset_or_label_absolute_in_catchall_directive1299);
            from=offset_or_label_absolute(address97);

            state._fsp--;

            pushFollow(FOLLOW_offset_or_label_absolute_in_catchall_directive1304);
            to=offset_or_label_absolute(address97);

            state._fsp--;

            pushFollow(FOLLOW_offset_or_label_absolute_in_catchall_directive1313);
            using=offset_or_label_absolute(address97);

            state._fsp--;


            match(input, Token.UP, null); 

            			int startAddress = from;
            			int endAddress = to;
            			int handlerAddress = using;

            			((method_scope)method_stack.peek()).tryList.addCatchAllHandler(startAddress, endAddress, handlerAddress);
            		

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "catchall_directive"


    // $ANTLR start "address"
    // org/jf/smali/smaliTreeWalker.g:735:1: address returns [int address] : I_ADDRESS ;
    public final int address() throws RecognitionException {
        int address = 0;

        CommonTree I_ADDRESS98=null;

        try {
            // org/jf/smali/smaliTreeWalker.g:736:2: ( I_ADDRESS )
            // org/jf/smali/smaliTreeWalker.g:736:4: I_ADDRESS
            {
            I_ADDRESS98=(CommonTree)match(input,I_ADDRESS,FOLLOW_I_ADDRESS_in_address1331); 

            			address = Integer.parseInt((I_ADDRESS98!=null?I_ADDRESS98.getText():null));
            		

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return address;
    }
    // $ANTLR end "address"


    // $ANTLR start "parameters"
    // org/jf/smali/smaliTreeWalker.g:741:1: parameters returns [AnnotationSetRefList parameterAnnotations] : ^( I_PARAMETERS ( parameter )* ) ;
    public final AnnotationSetRefList parameters() throws RecognitionException {
        AnnotationSetRefList parameterAnnotations = null;

        AnnotationSetItem parameter99 = null;



        		int parameterCount = 0;
        		List<AnnotationSetItem> annotationSetItems = new ArrayList<AnnotationSetItem>();
        	
        try {
            // org/jf/smali/smaliTreeWalker.g:747:2: ( ^( I_PARAMETERS ( parameter )* ) )
            // org/jf/smali/smaliTreeWalker.g:747:4: ^( I_PARAMETERS ( parameter )* )
            {
            match(input,I_PARAMETERS,FOLLOW_I_PARAMETERS_in_parameters1355); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // org/jf/smali/smaliTreeWalker.g:747:19: ( parameter )*
                loop25:
                do {
                    int alt25=2;
                    switch ( input.LA(1) ) {
                    case I_PARAMETER:
                        {
                        alt25=1;
                        }
                        break;

                    }

                    switch (alt25) {
                	case 1 :
                	    // org/jf/smali/smaliTreeWalker.g:747:20: parameter
                	    {
                	    pushFollow(FOLLOW_parameter_in_parameters1358);
                	    parameter99=parameter();

                	    state._fsp--;


                	    					if (parameter99 != null) {
                	    						while (annotationSetItems.size() < parameterCount) {
                	    							annotationSetItems.add(AnnotationSetItem.internAnnotationSetItem(dexFile, null));
                	    						}
                	    						annotationSetItems.add(parameter99);
                	    					}

                	    					parameterCount++;
                	    				

                	    }
                	    break;

                	default :
                	    break loop25;
                    }
                } while (true);


                match(input, Token.UP, null); 
            }

            			if (annotationSetItems.size() > 0) {
            				while (annotationSetItems.size() < parameterCount) {
            					annotationSetItems.add(AnnotationSetItem.internAnnotationSetItem(dexFile, null));
            				}
            				parameterAnnotations = AnnotationSetRefList.internAnnotationSetRefList(dexFile, annotationSetItems);
            			}
            		

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return parameterAnnotations;
    }
    // $ANTLR end "parameters"


    // $ANTLR start "parameter"
    // org/jf/smali/smaliTreeWalker.g:768:1: parameter returns [AnnotationSetItem parameterAnnotationSet] : ^( I_PARAMETER ( string_literal | ) annotations ) ;
    public final AnnotationSetItem parameter() throws RecognitionException {
        AnnotationSetItem parameterAnnotationSet = null;

        String string_literal100 = null;

        AnnotationSetItem annotations101 = null;


        try {
            // org/jf/smali/smaliTreeWalker.g:769:2: ( ^( I_PARAMETER ( string_literal | ) annotations ) )
            // org/jf/smali/smaliTreeWalker.g:769:4: ^( I_PARAMETER ( string_literal | ) annotations )
            {
            match(input,I_PARAMETER,FOLLOW_I_PARAMETER_in_parameter1387); 

            match(input, Token.DOWN, null); 
            // org/jf/smali/smaliTreeWalker.g:769:19: ( string_literal | )
            int alt26=2;
            switch ( input.LA(1) ) {
            case STRING_LITERAL:
                {
                alt26=1;
                }
                break;
            case I_ANNOTATIONS:
                {
                alt26=2;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 26, 0, input);

                throw nvae;
            }

            switch (alt26) {
                case 1 :
                    // org/jf/smali/smaliTreeWalker.g:769:21: string_literal
                    {
                    pushFollow(FOLLOW_string_literal_in_parameter1392);
                    string_literal100=string_literal();

                    state._fsp--;

                    ((method_scope)method_stack.peek()).debugInfo.addParameterName(string_literal100);

                    }
                    break;
                case 2 :
                    // org/jf/smali/smaliTreeWalker.g:770:7: 
                    {
                    ((method_scope)method_stack.peek()).debugInfo.addParameterName(null);

                    }
                    break;

            }

            pushFollow(FOLLOW_annotations_in_parameter1414);
            annotations101=annotations();

            state._fsp--;

            parameterAnnotationSet = annotations101;

            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return parameterAnnotationSet;
    }
    // $ANTLR end "parameter"


    // $ANTLR start "ordered_debug_directives"
    // org/jf/smali/smaliTreeWalker.g:775:1: ordered_debug_directives[int totalMethodRegisters, int methodParameterRegisters] : ^( I_ORDERED_DEBUG_DIRECTIVES ( line | local[$totalMethodRegisters, $methodParameterRegisters] | end_local[$totalMethodRegisters, $methodParameterRegisters] | restart_local[$totalMethodRegisters, $methodParameterRegisters] | prologue | epilogue | source )* ) ;
    public final void ordered_debug_directives(int totalMethodRegisters, int methodParameterRegisters) throws RecognitionException {
        try {
            // org/jf/smali/smaliTreeWalker.g:776:2: ( ^( I_ORDERED_DEBUG_DIRECTIVES ( line | local[$totalMethodRegisters, $methodParameterRegisters] | end_local[$totalMethodRegisters, $methodParameterRegisters] | restart_local[$totalMethodRegisters, $methodParameterRegisters] | prologue | epilogue | source )* ) )
            // org/jf/smali/smaliTreeWalker.g:776:4: ^( I_ORDERED_DEBUG_DIRECTIVES ( line | local[$totalMethodRegisters, $methodParameterRegisters] | end_local[$totalMethodRegisters, $methodParameterRegisters] | restart_local[$totalMethodRegisters, $methodParameterRegisters] | prologue | epilogue | source )* )
            {
            match(input,I_ORDERED_DEBUG_DIRECTIVES,FOLLOW_I_ORDERED_DEBUG_DIRECTIVES_in_ordered_debug_directives1431); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // org/jf/smali/smaliTreeWalker.g:776:34: ( line | local[$totalMethodRegisters, $methodParameterRegisters] | end_local[$totalMethodRegisters, $methodParameterRegisters] | restart_local[$totalMethodRegisters, $methodParameterRegisters] | prologue | epilogue | source )*
                loop27:
                do {
                    int alt27=8;
                    switch ( input.LA(1) ) {
                    case I_LINE:
                        {
                        alt27=1;
                        }
                        break;
                    case I_LOCAL:
                        {
                        alt27=2;
                        }
                        break;
                    case I_END_LOCAL:
                        {
                        alt27=3;
                        }
                        break;
                    case I_RESTART_LOCAL:
                        {
                        alt27=4;
                        }
                        break;
                    case I_PROLOGUE:
                        {
                        alt27=5;
                        }
                        break;
                    case I_EPILOGUE:
                        {
                        alt27=6;
                        }
                        break;
                    case I_SOURCE:
                        {
                        alt27=7;
                        }
                        break;

                    }

                    switch (alt27) {
                	case 1 :
                	    // org/jf/smali/smaliTreeWalker.g:776:36: line
                	    {
                	    pushFollow(FOLLOW_line_in_ordered_debug_directives1436);
                	    line();

                	    state._fsp--;


                	    }
                	    break;
                	case 2 :
                	    // org/jf/smali/smaliTreeWalker.g:777:10: local[$totalMethodRegisters, $methodParameterRegisters]
                	    {
                	    pushFollow(FOLLOW_local_in_ordered_debug_directives1447);
                	    local(totalMethodRegisters, methodParameterRegisters);

                	    state._fsp--;


                	    }
                	    break;
                	case 3 :
                	    // org/jf/smali/smaliTreeWalker.g:778:9: end_local[$totalMethodRegisters, $methodParameterRegisters]
                	    {
                	    pushFollow(FOLLOW_end_local_in_ordered_debug_directives1458);
                	    end_local(totalMethodRegisters, methodParameterRegisters);

                	    state._fsp--;


                	    }
                	    break;
                	case 4 :
                	    // org/jf/smali/smaliTreeWalker.g:779:9: restart_local[$totalMethodRegisters, $methodParameterRegisters]
                	    {
                	    pushFollow(FOLLOW_restart_local_in_ordered_debug_directives1469);
                	    restart_local(totalMethodRegisters, methodParameterRegisters);

                	    state._fsp--;


                	    }
                	    break;
                	case 5 :
                	    // org/jf/smali/smaliTreeWalker.g:780:9: prologue
                	    {
                	    pushFollow(FOLLOW_prologue_in_ordered_debug_directives1480);
                	    prologue();

                	    state._fsp--;


                	    }
                	    break;
                	case 6 :
                	    // org/jf/smali/smaliTreeWalker.g:781:9: epilogue
                	    {
                	    pushFollow(FOLLOW_epilogue_in_ordered_debug_directives1490);
                	    epilogue();

                	    state._fsp--;


                	    }
                	    break;
                	case 7 :
                	    // org/jf/smali/smaliTreeWalker.g:782:9: source
                	    {
                	    pushFollow(FOLLOW_source_in_ordered_debug_directives1500);
                	    source();

                	    state._fsp--;


                	    }
                	    break;

                	default :
                	    break loop27;
                    }
                } while (true);


                match(input, Token.UP, null); 
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "ordered_debug_directives"


    // $ANTLR start "line"
    // org/jf/smali/smaliTreeWalker.g:785:1: line : ^( I_LINE integral_literal address ) ;
    public final void line() throws RecognitionException {
        int address102 = 0;

        int integral_literal103 = 0;


        try {
            // org/jf/smali/smaliTreeWalker.g:786:2: ( ^( I_LINE integral_literal address ) )
            // org/jf/smali/smaliTreeWalker.g:786:4: ^( I_LINE integral_literal address )
            {
            match(input,I_LINE,FOLLOW_I_LINE_in_line1520); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_integral_literal_in_line1522);
            integral_literal103=integral_literal();

            state._fsp--;

            pushFollow(FOLLOW_address_in_line1524);
            address102=address();

            state._fsp--;


            match(input, Token.UP, null); 

            			((method_scope)method_stack.peek()).debugInfo.addLine(address102, integral_literal103);
            		

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "line"


    // $ANTLR start "local"
    // org/jf/smali/smaliTreeWalker.g:791:1: local[int totalMethodRegisters, int methodParameterRegisters] : ^( I_LOCAL REGISTER SIMPLE_NAME nonvoid_type_descriptor ( string_literal )? address ) ;
    public final void local(int totalMethodRegisters, int methodParameterRegisters) throws RecognitionException {
        CommonTree REGISTER104=null;
        CommonTree SIMPLE_NAME107=null;
        String string_literal105 = null;

        int address106 = 0;

        smaliTreeWalker.nonvoid_type_descriptor_return nonvoid_type_descriptor108 = null;


        try {
            // org/jf/smali/smaliTreeWalker.g:792:2: ( ^( I_LOCAL REGISTER SIMPLE_NAME nonvoid_type_descriptor ( string_literal )? address ) )
            // org/jf/smali/smaliTreeWalker.g:792:4: ^( I_LOCAL REGISTER SIMPLE_NAME nonvoid_type_descriptor ( string_literal )? address )
            {
            match(input,I_LOCAL,FOLLOW_I_LOCAL_in_local1540); 

            match(input, Token.DOWN, null); 
            REGISTER104=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_local1542); 
            SIMPLE_NAME107=(CommonTree)match(input,SIMPLE_NAME,FOLLOW_SIMPLE_NAME_in_local1544); 
            pushFollow(FOLLOW_nonvoid_type_descriptor_in_local1546);
            nonvoid_type_descriptor108=nonvoid_type_descriptor();

            state._fsp--;

            // org/jf/smali/smaliTreeWalker.g:792:59: ( string_literal )?
            int alt28=2;
            switch ( input.LA(1) ) {
                case STRING_LITERAL:
                    {
                    alt28=1;
                    }
                    break;
            }

            switch (alt28) {
                case 1 :
                    // org/jf/smali/smaliTreeWalker.g:792:59: string_literal
                    {
                    pushFollow(FOLLOW_string_literal_in_local1548);
                    string_literal105=string_literal();

                    state._fsp--;


                    }
                    break;

            }

            pushFollow(FOLLOW_address_in_local1551);
            address106=address();

            state._fsp--;


            match(input, Token.UP, null); 

            			int registerNumber = parseRegister_short((REGISTER104!=null?REGISTER104.getText():null), totalMethodRegisters, methodParameterRegisters);

            			if (string_literal105 != null) {
            				((method_scope)method_stack.peek()).debugInfo.addLocalExtended(address106, registerNumber, (SIMPLE_NAME107!=null?SIMPLE_NAME107.getText():null), (nonvoid_type_descriptor108!=null?nonvoid_type_descriptor108.type:null).getTypeDescriptor(), string_literal105);
            			} else {
            				((method_scope)method_stack.peek()).debugInfo.addLocal(address106, registerNumber, (SIMPLE_NAME107!=null?SIMPLE_NAME107.getText():null), (nonvoid_type_descriptor108!=null?nonvoid_type_descriptor108.type:null).getTypeDescriptor());
            			}
            		

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "local"


    // $ANTLR start "end_local"
    // org/jf/smali/smaliTreeWalker.g:803:1: end_local[int totalMethodRegisters, int methodParameterRegisters] : ^( I_END_LOCAL REGISTER address ) ;
    public final void end_local(int totalMethodRegisters, int methodParameterRegisters) throws RecognitionException {
        CommonTree REGISTER109=null;
        int address110 = 0;


        try {
            // org/jf/smali/smaliTreeWalker.g:804:2: ( ^( I_END_LOCAL REGISTER address ) )
            // org/jf/smali/smaliTreeWalker.g:804:4: ^( I_END_LOCAL REGISTER address )
            {
            match(input,I_END_LOCAL,FOLLOW_I_END_LOCAL_in_end_local1567); 

            match(input, Token.DOWN, null); 
            REGISTER109=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_end_local1569); 
            pushFollow(FOLLOW_address_in_end_local1571);
            address110=address();

            state._fsp--;


            match(input, Token.UP, null); 

            			int registerNumber = parseRegister_short((REGISTER109!=null?REGISTER109.getText():null), totalMethodRegisters, methodParameterRegisters);

            			((method_scope)method_stack.peek()).debugInfo.addEndLocal(address110, registerNumber);
            		

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "end_local"


    // $ANTLR start "restart_local"
    // org/jf/smali/smaliTreeWalker.g:811:1: restart_local[int totalMethodRegisters, int methodParameterRegisters] : ^( I_RESTART_LOCAL REGISTER address ) ;
    public final void restart_local(int totalMethodRegisters, int methodParameterRegisters) throws RecognitionException {
        CommonTree REGISTER111=null;
        int address112 = 0;


        try {
            // org/jf/smali/smaliTreeWalker.g:812:2: ( ^( I_RESTART_LOCAL REGISTER address ) )
            // org/jf/smali/smaliTreeWalker.g:812:4: ^( I_RESTART_LOCAL REGISTER address )
            {
            match(input,I_RESTART_LOCAL,FOLLOW_I_RESTART_LOCAL_in_restart_local1587); 

            match(input, Token.DOWN, null); 
            REGISTER111=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_restart_local1589); 
            pushFollow(FOLLOW_address_in_restart_local1591);
            address112=address();

            state._fsp--;


            match(input, Token.UP, null); 

            			int registerNumber = parseRegister_short((REGISTER111!=null?REGISTER111.getText():null), totalMethodRegisters, methodParameterRegisters);

            			((method_scope)method_stack.peek()).debugInfo.addRestartLocal(address112, registerNumber);
            		

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "restart_local"


    // $ANTLR start "prologue"
    // org/jf/smali/smaliTreeWalker.g:819:1: prologue : ^( I_PROLOGUE address ) ;
    public final void prologue() throws RecognitionException {
        int address113 = 0;


        try {
            // org/jf/smali/smaliTreeWalker.g:820:2: ( ^( I_PROLOGUE address ) )
            // org/jf/smali/smaliTreeWalker.g:820:4: ^( I_PROLOGUE address )
            {
            match(input,I_PROLOGUE,FOLLOW_I_PROLOGUE_in_prologue1606); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_address_in_prologue1608);
            address113=address();

            state._fsp--;


            match(input, Token.UP, null); 

            			((method_scope)method_stack.peek()).debugInfo.addPrologue(address113);
            		

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "prologue"


    // $ANTLR start "epilogue"
    // org/jf/smali/smaliTreeWalker.g:825:1: epilogue : ^( I_EPILOGUE address ) ;
    public final void epilogue() throws RecognitionException {
        int address114 = 0;


        try {
            // org/jf/smali/smaliTreeWalker.g:826:2: ( ^( I_EPILOGUE address ) )
            // org/jf/smali/smaliTreeWalker.g:826:4: ^( I_EPILOGUE address )
            {
            match(input,I_EPILOGUE,FOLLOW_I_EPILOGUE_in_epilogue1623); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_address_in_epilogue1625);
            address114=address();

            state._fsp--;


            match(input, Token.UP, null); 

            			((method_scope)method_stack.peek()).debugInfo.addEpilogue(address114);
            		

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "epilogue"


    // $ANTLR start "source"
    // org/jf/smali/smaliTreeWalker.g:831:1: source : ^( I_SOURCE string_literal address ) ;
    public final void source() throws RecognitionException {
        int address115 = 0;

        String string_literal116 = null;


        try {
            // org/jf/smali/smaliTreeWalker.g:832:2: ( ^( I_SOURCE string_literal address ) )
            // org/jf/smali/smaliTreeWalker.g:832:4: ^( I_SOURCE string_literal address )
            {
            match(input,I_SOURCE,FOLLOW_I_SOURCE_in_source1640); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_string_literal_in_source1642);
            string_literal116=string_literal();

            state._fsp--;

            pushFollow(FOLLOW_address_in_source1644);
            address115=address();

            state._fsp--;


            match(input, Token.UP, null); 

            			((method_scope)method_stack.peek()).debugInfo.addSetFile(address115, string_literal116);
            		

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "source"

    public static class statements_return extends TreeRuleReturnScope {
        public List<Instruction> instructions;
        public int maxOutRegisters;
    };

    // $ANTLR start "statements"
    // org/jf/smali/smaliTreeWalker.g:837:1: statements[int totalMethodRegisters, int methodParameterRegisters] returns [List<Instruction> instructions, int maxOutRegisters] : ^( I_STATEMENTS ( instruction[$totalMethodRegisters, $methodParameterRegisters, $instructions] )* ) ;
    public final smaliTreeWalker.statements_return statements(int totalMethodRegisters, int methodParameterRegisters) throws RecognitionException {
        smaliTreeWalker.statements_return retval = new smaliTreeWalker.statements_return();
        retval.start = input.LT(1);

        int instruction117 = 0;



        		retval.instructions = new LinkedList<Instruction>();
        		retval.maxOutRegisters = 0;
        	
        try {
            // org/jf/smali/smaliTreeWalker.g:843:2: ( ^( I_STATEMENTS ( instruction[$totalMethodRegisters, $methodParameterRegisters, $instructions] )* ) )
            // org/jf/smali/smaliTreeWalker.g:843:4: ^( I_STATEMENTS ( instruction[$totalMethodRegisters, $methodParameterRegisters, $instructions] )* )
            {
            match(input,I_STATEMENTS,FOLLOW_I_STATEMENTS_in_statements1670); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // org/jf/smali/smaliTreeWalker.g:843:19: ( instruction[$totalMethodRegisters, $methodParameterRegisters, $instructions] )*
                loop29:
                do {
                    int alt29=2;
                    switch ( input.LA(1) ) {
                    case I_STATEMENT_FORMAT10t:
                    case I_STATEMENT_FORMAT10x:
                    case I_STATEMENT_FORMAT11n:
                    case I_STATEMENT_FORMAT11x:
                    case I_STATEMENT_FORMAT12x:
                    case I_STATEMENT_FORMAT20t:
                    case I_STATEMENT_FORMAT21c_TYPE:
                    case I_STATEMENT_FORMAT21c_FIELD:
                    case I_STATEMENT_FORMAT21c_STRING:
                    case I_STATEMENT_FORMAT21h:
                    case I_STATEMENT_FORMAT21s:
                    case I_STATEMENT_FORMAT21t:
                    case I_STATEMENT_FORMAT22b:
                    case I_STATEMENT_FORMAT22c_FIELD:
                    case I_STATEMENT_FORMAT22c_TYPE:
                    case I_STATEMENT_FORMAT22s:
                    case I_STATEMENT_FORMAT22t:
                    case I_STATEMENT_FORMAT22x:
                    case I_STATEMENT_FORMAT23x:
                    case I_STATEMENT_FORMAT30t:
                    case I_STATEMENT_FORMAT31c:
                    case I_STATEMENT_FORMAT31i:
                    case I_STATEMENT_FORMAT31t:
                    case I_STATEMENT_FORMAT32x:
                    case I_STATEMENT_FORMAT35c_METHOD:
                    case I_STATEMENT_FORMAT35c_TYPE:
                    case I_STATEMENT_FORMAT3rc_METHOD:
                    case I_STATEMENT_FORMAT3rc_TYPE:
                    case I_STATEMENT_FORMAT51l:
                    case I_STATEMENT_ARRAY_DATA:
                    case I_STATEMENT_PACKED_SWITCH:
                    case I_STATEMENT_SPARSE_SWITCH:
                        {
                        alt29=1;
                        }
                        break;

                    }

                    switch (alt29) {
                	case 1 :
                	    // org/jf/smali/smaliTreeWalker.g:843:20: instruction[$totalMethodRegisters, $methodParameterRegisters, $instructions]
                	    {
                	    pushFollow(FOLLOW_instruction_in_statements1673);
                	    instruction117=instruction(totalMethodRegisters, methodParameterRegisters, retval.instructions);

                	    state._fsp--;


                	    					((method_scope)method_stack.peek()).currentAddress += retval.instructions.get(retval.instructions.size() - 1).getSize(((method_scope)method_stack.peek()).currentAddress);
                	    					if (retval.maxOutRegisters < instruction117) {
                	    						retval.maxOutRegisters = instruction117;
                	    					}
                	    				

                	    }
                	    break;

                	default :
                	    break loop29;
                    }
                } while (true);


                match(input, Token.UP, null); 
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "statements"


    // $ANTLR start "label_ref"
    // org/jf/smali/smaliTreeWalker.g:851:1: label_ref returns [int labelAddress] : SIMPLE_NAME ;
    public final int label_ref() throws RecognitionException {
        int labelAddress = 0;

        CommonTree SIMPLE_NAME118=null;

        try {
            // org/jf/smali/smaliTreeWalker.g:852:2: ( SIMPLE_NAME )
            // org/jf/smali/smaliTreeWalker.g:852:4: SIMPLE_NAME
            {
            SIMPLE_NAME118=(CommonTree)match(input,SIMPLE_NAME,FOLLOW_SIMPLE_NAME_in_label_ref1695); 

            			Integer labelAdd = ((method_scope)method_stack.peek()).labels.get((SIMPLE_NAME118!=null?SIMPLE_NAME118.getText():null));

            			if (labelAdd == null) {
            				throw new SemanticException(input, SIMPLE_NAME118, "Label \"" + (SIMPLE_NAME118!=null?SIMPLE_NAME118.getText():null) + "\" is not defined.");
            			}

            			labelAddress = labelAdd;
            		

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return labelAddress;
    }
    // $ANTLR end "label_ref"


    // $ANTLR start "offset"
    // org/jf/smali/smaliTreeWalker.g:863:1: offset returns [int offsetValue] : OFFSET ;
    public final int offset() throws RecognitionException {
        int offsetValue = 0;

        CommonTree OFFSET119=null;

        try {
            // org/jf/smali/smaliTreeWalker.g:864:2: ( OFFSET )
            // org/jf/smali/smaliTreeWalker.g:864:4: OFFSET
            {
            OFFSET119=(CommonTree)match(input,OFFSET,FOLLOW_OFFSET_in_offset1711); 

            			String offsetText = (OFFSET119!=null?OFFSET119.getText():null);
            			if (offsetText.charAt(0) == '+') {
            				offsetText = offsetText.substring(1);
            			}
            			offsetValue = literalTools.parseInt(offsetText);
            		

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return offsetValue;
    }
    // $ANTLR end "offset"


    // $ANTLR start "offset_or_label_absolute"
    // org/jf/smali/smaliTreeWalker.g:873:1: offset_or_label_absolute[int baseAddress] returns [int address] : ( offset | label_ref );
    public final int offset_or_label_absolute(int baseAddress) throws RecognitionException {
        int address = 0;

        int offset120 = 0;

        int label_ref121 = 0;


        try {
            // org/jf/smali/smaliTreeWalker.g:874:2: ( offset | label_ref )
            int alt30=2;
            switch ( input.LA(1) ) {
            case OFFSET:
                {
                alt30=1;
                }
                break;
            case SIMPLE_NAME:
                {
                alt30=2;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 30, 0, input);

                throw nvae;
            }

            switch (alt30) {
                case 1 :
                    // org/jf/smali/smaliTreeWalker.g:874:4: offset
                    {
                    pushFollow(FOLLOW_offset_in_offset_or_label_absolute1728);
                    offset120=offset();

                    state._fsp--;

                    address = offset120 + baseAddress;

                    }
                    break;
                case 2 :
                    // org/jf/smali/smaliTreeWalker.g:875:4: label_ref
                    {
                    pushFollow(FOLLOW_label_ref_in_offset_or_label_absolute1735);
                    label_ref121=label_ref();

                    state._fsp--;

                    address = label_ref121;

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return address;
    }
    // $ANTLR end "offset_or_label_absolute"

    public static class offset_or_label_return extends TreeRuleReturnScope {
        public int offsetValue;
    };

    // $ANTLR start "offset_or_label"
    // org/jf/smali/smaliTreeWalker.g:877:1: offset_or_label returns [int offsetValue] : ( offset | label_ref );
    public final smaliTreeWalker.offset_or_label_return offset_or_label() throws RecognitionException {
        smaliTreeWalker.offset_or_label_return retval = new smaliTreeWalker.offset_or_label_return();
        retval.start = input.LT(1);

        int offset122 = 0;

        int label_ref123 = 0;


        try {
            // org/jf/smali/smaliTreeWalker.g:878:2: ( offset | label_ref )
            int alt31=2;
            switch ( input.LA(1) ) {
            case OFFSET:
                {
                alt31=1;
                }
                break;
            case SIMPLE_NAME:
                {
                alt31=2;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 31, 0, input);

                throw nvae;
            }

            switch (alt31) {
                case 1 :
                    // org/jf/smali/smaliTreeWalker.g:878:4: offset
                    {
                    pushFollow(FOLLOW_offset_in_offset_or_label1749);
                    offset122=offset();

                    state._fsp--;

                    retval.offsetValue = offset122;

                    }
                    break;
                case 2 :
                    // org/jf/smali/smaliTreeWalker.g:879:4: label_ref
                    {
                    pushFollow(FOLLOW_label_ref_in_offset_or_label1756);
                    label_ref123=label_ref();

                    state._fsp--;

                    retval.offsetValue = label_ref123-((method_scope)method_stack.peek()).currentAddress;

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "offset_or_label"

    public static class register_list_return extends TreeRuleReturnScope {
        public byte[] registers;
        public byte registerCount;
    };

    // $ANTLR start "register_list"
    // org/jf/smali/smaliTreeWalker.g:882:1: register_list[int totalMethodRegisters, int methodParameterRegisters] returns [byte[] registers, byte registerCount] : ^( I_REGISTER_LIST ( REGISTER )* ) ;
    public final smaliTreeWalker.register_list_return register_list(int totalMethodRegisters, int methodParameterRegisters) throws RecognitionException {
        smaliTreeWalker.register_list_return retval = new smaliTreeWalker.register_list_return();
        retval.start = input.LT(1);

        CommonTree I_REGISTER_LIST124=null;
        CommonTree REGISTER125=null;


        		retval.registers = new byte[5];
        		retval.registerCount = 0;
        	
        try {
            // org/jf/smali/smaliTreeWalker.g:888:2: ( ^( I_REGISTER_LIST ( REGISTER )* ) )
            // org/jf/smali/smaliTreeWalker.g:888:4: ^( I_REGISTER_LIST ( REGISTER )* )
            {
            I_REGISTER_LIST124=(CommonTree)match(input,I_REGISTER_LIST,FOLLOW_I_REGISTER_LIST_in_register_list1780); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // org/jf/smali/smaliTreeWalker.g:889:4: ( REGISTER )*
                loop32:
                do {
                    int alt32=2;
                    switch ( input.LA(1) ) {
                    case REGISTER:
                        {
                        alt32=1;
                        }
                        break;

                    }

                    switch (alt32) {
                	case 1 :
                	    // org/jf/smali/smaliTreeWalker.g:889:5: REGISTER
                	    {
                	    REGISTER125=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_register_list1786); 

                	    				if (retval.registerCount == 5) {
                	    					throw new SemanticException(input, I_REGISTER_LIST124, "A list of registers can only have a maximum of 5 registers. Use the <op>/range alternate opcode instead.");
                	    				}
                	    				retval.registers[retval.registerCount++] = parseRegister_nibble((REGISTER125!=null?REGISTER125.getText():null), totalMethodRegisters, methodParameterRegisters);
                	    			

                	    }
                	    break;

                	default :
                	    break loop32;
                    }
                } while (true);


                match(input, Token.UP, null); 
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "register_list"

    public static class register_range_return extends TreeRuleReturnScope {
        public int startRegister;
        public int endRegister;
    };

    // $ANTLR start "register_range"
    // org/jf/smali/smaliTreeWalker.g:897:1: register_range[int totalMethodRegisters, int methodParameterRegisters] returns [int startRegister, int endRegister] : ^( I_REGISTER_RANGE startReg= REGISTER (endReg= REGISTER )? ) ;
    public final smaliTreeWalker.register_range_return register_range(int totalMethodRegisters, int methodParameterRegisters) throws RecognitionException {
        smaliTreeWalker.register_range_return retval = new smaliTreeWalker.register_range_return();
        retval.start = input.LT(1);

        CommonTree startReg=null;
        CommonTree endReg=null;
        CommonTree I_REGISTER_RANGE126=null;

        try {
            // org/jf/smali/smaliTreeWalker.g:898:2: ( ^( I_REGISTER_RANGE startReg= REGISTER (endReg= REGISTER )? ) )
            // org/jf/smali/smaliTreeWalker.g:898:4: ^( I_REGISTER_RANGE startReg= REGISTER (endReg= REGISTER )? )
            {
            I_REGISTER_RANGE126=(CommonTree)match(input,I_REGISTER_RANGE,FOLLOW_I_REGISTER_RANGE_in_register_range1808); 

            match(input, Token.DOWN, null); 
            startReg=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_register_range1812); 
            // org/jf/smali/smaliTreeWalker.g:898:47: (endReg= REGISTER )?
            int alt33=2;
            switch ( input.LA(1) ) {
                case REGISTER:
                    {
                    alt33=1;
                    }
                    break;
            }

            switch (alt33) {
                case 1 :
                    // org/jf/smali/smaliTreeWalker.g:898:47: endReg= REGISTER
                    {
                    endReg=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_register_range1816); 

                    }
                    break;

            }


            match(input, Token.UP, null); 

            			retval.startRegister = parseRegister_short((startReg!=null?startReg.getText():null), totalMethodRegisters, methodParameterRegisters);
            			if (endReg == null) {
            				retval.endRegister = retval.startRegister;
            			} else {
            				retval.endRegister = parseRegister_short((endReg!=null?endReg.getText():null), totalMethodRegisters, methodParameterRegisters);
            			}

            			int registerCount = retval.endRegister-retval.startRegister+1;
            			if (registerCount > 256) {
            				throw new SemanticException(input, I_REGISTER_RANGE126, "A register range can span a maximum of 256 registers");
            			}
            			if (registerCount < 1) {
            				throw new SemanticException(input, I_REGISTER_RANGE126, "A register range must have the lower register listed first");
            			}
            		

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "register_range"


    // $ANTLR start "instruction"
    // org/jf/smali/smaliTreeWalker.g:917:1: instruction[int totalMethodRegisters, int methodParameterRegisters, List<Instruction> instructions] returns [int outRegisters] : ( ^( I_STATEMENT_FORMAT10t INSTRUCTION_FORMAT10t offset_or_label ) | ^( I_STATEMENT_FORMAT10x INSTRUCTION_FORMAT10x ) | ^( I_STATEMENT_FORMAT11n INSTRUCTION_FORMAT11n REGISTER short_integral_literal ) | ^( I_STATEMENT_FORMAT11x INSTRUCTION_FORMAT11x REGISTER ) | ^( I_STATEMENT_FORMAT12x INSTRUCTION_FORMAT12x registerA= REGISTER registerB= REGISTER ) | ^( I_STATEMENT_FORMAT20t INSTRUCTION_FORMAT20t offset_or_label ) | ^( I_STATEMENT_FORMAT21c_FIELD INSTRUCTION_FORMAT21c_FIELD REGISTER fully_qualified_field ) | ^( I_STATEMENT_FORMAT21c_STRING INSTRUCTION_FORMAT21c_STRING REGISTER string_literal ) | ^( I_STATEMENT_FORMAT21c_TYPE INSTRUCTION_FORMAT21c_TYPE REGISTER reference_type_descriptor ) | ^( I_STATEMENT_FORMAT21h INSTRUCTION_FORMAT21h REGISTER short_integral_literal ) | ^( I_STATEMENT_FORMAT21s INSTRUCTION_FORMAT21s REGISTER short_integral_literal ) | ^( I_STATEMENT_FORMAT21t INSTRUCTION_FORMAT21t REGISTER offset_or_label ) | ^( I_STATEMENT_FORMAT22b INSTRUCTION_FORMAT22b registerA= REGISTER registerB= REGISTER short_integral_literal ) | ^( I_STATEMENT_FORMAT22c_FIELD INSTRUCTION_FORMAT22c_FIELD registerA= REGISTER registerB= REGISTER fully_qualified_field ) | ^( I_STATEMENT_FORMAT22c_TYPE INSTRUCTION_FORMAT22c_TYPE registerA= REGISTER registerB= REGISTER nonvoid_type_descriptor ) | ^( I_STATEMENT_FORMAT22s INSTRUCTION_FORMAT22s registerA= REGISTER registerB= REGISTER short_integral_literal ) | ^( I_STATEMENT_FORMAT22t INSTRUCTION_FORMAT22t registerA= REGISTER registerB= REGISTER offset_or_label ) | ^( I_STATEMENT_FORMAT22x INSTRUCTION_FORMAT22x registerA= REGISTER registerB= REGISTER ) | ^( I_STATEMENT_FORMAT23x INSTRUCTION_FORMAT23x registerA= REGISTER registerB= REGISTER registerC= REGISTER ) | ^( I_STATEMENT_FORMAT30t INSTRUCTION_FORMAT30t offset_or_label ) | ^( I_STATEMENT_FORMAT31c INSTRUCTION_FORMAT31c REGISTER string_literal ) | ^( I_STATEMENT_FORMAT31i INSTRUCTION_FORMAT31i REGISTER fixed_32bit_literal ) | ^( I_STATEMENT_FORMAT31t INSTRUCTION_FORMAT31t REGISTER offset_or_label ) | ^( I_STATEMENT_FORMAT32x INSTRUCTION_FORMAT32x registerA= REGISTER registerB= REGISTER ) | ^( I_STATEMENT_FORMAT35c_METHOD INSTRUCTION_FORMAT35c_METHOD register_list[$totalMethodRegisters, $methodParameterRegisters] fully_qualified_method ) | ^( I_STATEMENT_FORMAT35c_TYPE INSTRUCTION_FORMAT35c_TYPE register_list[$totalMethodRegisters, $methodParameterRegisters] nonvoid_type_descriptor ) | ^( I_STATEMENT_FORMAT3rc_METHOD INSTRUCTION_FORMAT3rc_METHOD register_range[$totalMethodRegisters, $methodParameterRegisters] fully_qualified_method ) | ^( I_STATEMENT_FORMAT3rc_TYPE INSTRUCTION_FORMAT3rc_TYPE register_range[$totalMethodRegisters, $methodParameterRegisters] nonvoid_type_descriptor ) | ^( I_STATEMENT_FORMAT51l INSTRUCTION_FORMAT51l REGISTER fixed_64bit_literal ) | ^( I_STATEMENT_ARRAY_DATA ^( I_ARRAY_ELEMENT_SIZE short_integral_literal ) array_elements ) | ^( I_STATEMENT_PACKED_SWITCH ^( I_PACKED_SWITCH_START_KEY fixed_32bit_literal ) packed_switch_targets[baseAddress] ) | ^( I_STATEMENT_SPARSE_SWITCH sparse_switch_target_count sparse_switch_keys[$sparse_switch_target_count.targetCount] sparse_switch_targets[baseAddress, $sparse_switch_target_count.targetCount] ) );
    public final int instruction(int totalMethodRegisters, int methodParameterRegisters, List<Instruction> instructions) throws RecognitionException {
        int outRegisters = 0;

        CommonTree registerA=null;
        CommonTree registerB=null;
        CommonTree registerC=null;
        CommonTree INSTRUCTION_FORMAT10t127=null;
        CommonTree INSTRUCTION_FORMAT10x129=null;
        CommonTree INSTRUCTION_FORMAT11n130=null;
        CommonTree REGISTER131=null;
        CommonTree INSTRUCTION_FORMAT11x133=null;
        CommonTree REGISTER134=null;
        CommonTree INSTRUCTION_FORMAT12x135=null;
        CommonTree INSTRUCTION_FORMAT20t136=null;
        CommonTree INSTRUCTION_FORMAT21c_FIELD138=null;
        CommonTree REGISTER139=null;
        CommonTree INSTRUCTION_FORMAT21c_STRING141=null;
        CommonTree REGISTER142=null;
        CommonTree INSTRUCTION_FORMAT21c_TYPE144=null;
        CommonTree REGISTER145=null;
        CommonTree INSTRUCTION_FORMAT21h147=null;
        CommonTree REGISTER148=null;
        CommonTree INSTRUCTION_FORMAT21s150=null;
        CommonTree REGISTER151=null;
        CommonTree INSTRUCTION_FORMAT21t153=null;
        CommonTree REGISTER154=null;
        CommonTree INSTRUCTION_FORMAT22b156=null;
        CommonTree INSTRUCTION_FORMAT22c_FIELD158=null;
        CommonTree INSTRUCTION_FORMAT22c_TYPE160=null;
        CommonTree INSTRUCTION_FORMAT22s162=null;
        CommonTree INSTRUCTION_FORMAT22t164=null;
        CommonTree INSTRUCTION_FORMAT22x166=null;
        CommonTree INSTRUCTION_FORMAT23x167=null;
        CommonTree INSTRUCTION_FORMAT30t168=null;
        CommonTree INSTRUCTION_FORMAT31c170=null;
        CommonTree REGISTER171=null;
        CommonTree INSTRUCTION_FORMAT31i173=null;
        CommonTree REGISTER174=null;
        CommonTree INSTRUCTION_FORMAT31t176=null;
        CommonTree REGISTER177=null;
        CommonTree INSTRUCTION_FORMAT32x179=null;
        CommonTree INSTRUCTION_FORMAT35c_METHOD180=null;
        CommonTree INSTRUCTION_FORMAT35c_TYPE183=null;
        CommonTree INSTRUCTION_FORMAT3rc_METHOD186=null;
        CommonTree INSTRUCTION_FORMAT3rc_TYPE189=null;
        CommonTree INSTRUCTION_FORMAT51l192=null;
        CommonTree REGISTER193=null;
        smaliTreeWalker.offset_or_label_return offset_or_label128 = null;

        short short_integral_literal132 = 0;

        smaliTreeWalker.offset_or_label_return offset_or_label137 = null;

        FieldIdItem fully_qualified_field140 = null;

        String string_literal143 = null;

        smaliTreeWalker.reference_type_descriptor_return reference_type_descriptor146 = null;

        short short_integral_literal149 = 0;

        short short_integral_literal152 = 0;

        smaliTreeWalker.offset_or_label_return offset_or_label155 = null;

        short short_integral_literal157 = 0;

        FieldIdItem fully_qualified_field159 = null;

        smaliTreeWalker.nonvoid_type_descriptor_return nonvoid_type_descriptor161 = null;

        short short_integral_literal163 = 0;

        smaliTreeWalker.offset_or_label_return offset_or_label165 = null;

        smaliTreeWalker.offset_or_label_return offset_or_label169 = null;

        String string_literal172 = null;

        int fixed_32bit_literal175 = 0;

        smaliTreeWalker.offset_or_label_return offset_or_label178 = null;

        smaliTreeWalker.register_list_return register_list181 = null;

        MethodIdItem fully_qualified_method182 = null;

        smaliTreeWalker.register_list_return register_list184 = null;

        smaliTreeWalker.nonvoid_type_descriptor_return nonvoid_type_descriptor185 = null;

        smaliTreeWalker.register_range_return register_range187 = null;

        MethodIdItem fully_qualified_method188 = null;

        smaliTreeWalker.register_range_return register_range190 = null;

        smaliTreeWalker.nonvoid_type_descriptor_return nonvoid_type_descriptor191 = null;

        long fixed_64bit_literal194 = 0;

        short short_integral_literal195 = 0;

        List<byte[]> array_elements196 = null;

        int fixed_32bit_literal197 = 0;

        int[] packed_switch_targets198 = null;

        int sparse_switch_target_count199 = 0;

        int[] sparse_switch_keys200 = null;

        int[] sparse_switch_targets201 = null;


        try {
            // org/jf/smali/smaliTreeWalker.g:918:2: ( ^( I_STATEMENT_FORMAT10t INSTRUCTION_FORMAT10t offset_or_label ) | ^( I_STATEMENT_FORMAT10x INSTRUCTION_FORMAT10x ) | ^( I_STATEMENT_FORMAT11n INSTRUCTION_FORMAT11n REGISTER short_integral_literal ) | ^( I_STATEMENT_FORMAT11x INSTRUCTION_FORMAT11x REGISTER ) | ^( I_STATEMENT_FORMAT12x INSTRUCTION_FORMAT12x registerA= REGISTER registerB= REGISTER ) | ^( I_STATEMENT_FORMAT20t INSTRUCTION_FORMAT20t offset_or_label ) | ^( I_STATEMENT_FORMAT21c_FIELD INSTRUCTION_FORMAT21c_FIELD REGISTER fully_qualified_field ) | ^( I_STATEMENT_FORMAT21c_STRING INSTRUCTION_FORMAT21c_STRING REGISTER string_literal ) | ^( I_STATEMENT_FORMAT21c_TYPE INSTRUCTION_FORMAT21c_TYPE REGISTER reference_type_descriptor ) | ^( I_STATEMENT_FORMAT21h INSTRUCTION_FORMAT21h REGISTER short_integral_literal ) | ^( I_STATEMENT_FORMAT21s INSTRUCTION_FORMAT21s REGISTER short_integral_literal ) | ^( I_STATEMENT_FORMAT21t INSTRUCTION_FORMAT21t REGISTER offset_or_label ) | ^( I_STATEMENT_FORMAT22b INSTRUCTION_FORMAT22b registerA= REGISTER registerB= REGISTER short_integral_literal ) | ^( I_STATEMENT_FORMAT22c_FIELD INSTRUCTION_FORMAT22c_FIELD registerA= REGISTER registerB= REGISTER fully_qualified_field ) | ^( I_STATEMENT_FORMAT22c_TYPE INSTRUCTION_FORMAT22c_TYPE registerA= REGISTER registerB= REGISTER nonvoid_type_descriptor ) | ^( I_STATEMENT_FORMAT22s INSTRUCTION_FORMAT22s registerA= REGISTER registerB= REGISTER short_integral_literal ) | ^( I_STATEMENT_FORMAT22t INSTRUCTION_FORMAT22t registerA= REGISTER registerB= REGISTER offset_or_label ) | ^( I_STATEMENT_FORMAT22x INSTRUCTION_FORMAT22x registerA= REGISTER registerB= REGISTER ) | ^( I_STATEMENT_FORMAT23x INSTRUCTION_FORMAT23x registerA= REGISTER registerB= REGISTER registerC= REGISTER ) | ^( I_STATEMENT_FORMAT30t INSTRUCTION_FORMAT30t offset_or_label ) | ^( I_STATEMENT_FORMAT31c INSTRUCTION_FORMAT31c REGISTER string_literal ) | ^( I_STATEMENT_FORMAT31i INSTRUCTION_FORMAT31i REGISTER fixed_32bit_literal ) | ^( I_STATEMENT_FORMAT31t INSTRUCTION_FORMAT31t REGISTER offset_or_label ) | ^( I_STATEMENT_FORMAT32x INSTRUCTION_FORMAT32x registerA= REGISTER registerB= REGISTER ) | ^( I_STATEMENT_FORMAT35c_METHOD INSTRUCTION_FORMAT35c_METHOD register_list[$totalMethodRegisters, $methodParameterRegisters] fully_qualified_method ) | ^( I_STATEMENT_FORMAT35c_TYPE INSTRUCTION_FORMAT35c_TYPE register_list[$totalMethodRegisters, $methodParameterRegisters] nonvoid_type_descriptor ) | ^( I_STATEMENT_FORMAT3rc_METHOD INSTRUCTION_FORMAT3rc_METHOD register_range[$totalMethodRegisters, $methodParameterRegisters] fully_qualified_method ) | ^( I_STATEMENT_FORMAT3rc_TYPE INSTRUCTION_FORMAT3rc_TYPE register_range[$totalMethodRegisters, $methodParameterRegisters] nonvoid_type_descriptor ) | ^( I_STATEMENT_FORMAT51l INSTRUCTION_FORMAT51l REGISTER fixed_64bit_literal ) | ^( I_STATEMENT_ARRAY_DATA ^( I_ARRAY_ELEMENT_SIZE short_integral_literal ) array_elements ) | ^( I_STATEMENT_PACKED_SWITCH ^( I_PACKED_SWITCH_START_KEY fixed_32bit_literal ) packed_switch_targets[baseAddress] ) | ^( I_STATEMENT_SPARSE_SWITCH sparse_switch_target_count sparse_switch_keys[$sparse_switch_target_count.targetCount] sparse_switch_targets[baseAddress, $sparse_switch_target_count.targetCount] ) )
            int alt34=32;
            switch ( input.LA(1) ) {
            case I_STATEMENT_FORMAT10t:
                {
                alt34=1;
                }
                break;
            case I_STATEMENT_FORMAT10x:
                {
                alt34=2;
                }
                break;
            case I_STATEMENT_FORMAT11n:
                {
                alt34=3;
                }
                break;
            case I_STATEMENT_FORMAT11x:
                {
                alt34=4;
                }
                break;
            case I_STATEMENT_FORMAT12x:
                {
                alt34=5;
                }
                break;
            case I_STATEMENT_FORMAT20t:
                {
                alt34=6;
                }
                break;
            case I_STATEMENT_FORMAT21c_FIELD:
                {
                alt34=7;
                }
                break;
            case I_STATEMENT_FORMAT21c_STRING:
                {
                alt34=8;
                }
                break;
            case I_STATEMENT_FORMAT21c_TYPE:
                {
                alt34=9;
                }
                break;
            case I_STATEMENT_FORMAT21h:
                {
                alt34=10;
                }
                break;
            case I_STATEMENT_FORMAT21s:
                {
                alt34=11;
                }
                break;
            case I_STATEMENT_FORMAT21t:
                {
                alt34=12;
                }
                break;
            case I_STATEMENT_FORMAT22b:
                {
                alt34=13;
                }
                break;
            case I_STATEMENT_FORMAT22c_FIELD:
                {
                alt34=14;
                }
                break;
            case I_STATEMENT_FORMAT22c_TYPE:
                {
                alt34=15;
                }
                break;
            case I_STATEMENT_FORMAT22s:
                {
                alt34=16;
                }
                break;
            case I_STATEMENT_FORMAT22t:
                {
                alt34=17;
                }
                break;
            case I_STATEMENT_FORMAT22x:
                {
                alt34=18;
                }
                break;
            case I_STATEMENT_FORMAT23x:
                {
                alt34=19;
                }
                break;
            case I_STATEMENT_FORMAT30t:
                {
                alt34=20;
                }
                break;
            case I_STATEMENT_FORMAT31c:
                {
                alt34=21;
                }
                break;
            case I_STATEMENT_FORMAT31i:
                {
                alt34=22;
                }
                break;
            case I_STATEMENT_FORMAT31t:
                {
                alt34=23;
                }
                break;
            case I_STATEMENT_FORMAT32x:
                {
                alt34=24;
                }
                break;
            case I_STATEMENT_FORMAT35c_METHOD:
                {
                alt34=25;
                }
                break;
            case I_STATEMENT_FORMAT35c_TYPE:
                {
                alt34=26;
                }
                break;
            case I_STATEMENT_FORMAT3rc_METHOD:
                {
                alt34=27;
                }
                break;
            case I_STATEMENT_FORMAT3rc_TYPE:
                {
                alt34=28;
                }
                break;
            case I_STATEMENT_FORMAT51l:
                {
                alt34=29;
                }
                break;
            case I_STATEMENT_ARRAY_DATA:
                {
                alt34=30;
                }
                break;
            case I_STATEMENT_PACKED_SWITCH:
                {
                alt34=31;
                }
                break;
            case I_STATEMENT_SPARSE_SWITCH:
                {
                alt34=32;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 34, 0, input);

                throw nvae;
            }

            switch (alt34) {
                case 1 :
                    // org/jf/smali/smaliTreeWalker.g:919:3: ^( I_STATEMENT_FORMAT10t INSTRUCTION_FORMAT10t offset_or_label )
                    {
                    outRegisters = 0;
                    match(input,I_STATEMENT_FORMAT10t,FOLLOW_I_STATEMENT_FORMAT10t_in_instruction1845); 

                    match(input, Token.DOWN, null); 
                    INSTRUCTION_FORMAT10t127=(CommonTree)match(input,INSTRUCTION_FORMAT10t,FOLLOW_INSTRUCTION_FORMAT10t_in_instruction1847); 
                    pushFollow(FOLLOW_offset_or_label_in_instruction1849);
                    offset_or_label128=offset_or_label();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    			Opcode opcode = Opcode.getOpcodeByName((INSTRUCTION_FORMAT10t127!=null?INSTRUCTION_FORMAT10t127.getText():null));

                    			int addressOffset = (offset_or_label128!=null?offset_or_label128.offsetValue:0);

                    			instructions.add(new Instruction10t(opcode, addressOffset));
                    		

                    }
                    break;
                case 2 :
                    // org/jf/smali/smaliTreeWalker.g:929:3: ^( I_STATEMENT_FORMAT10x INSTRUCTION_FORMAT10x )
                    {
                    match(input,I_STATEMENT_FORMAT10x,FOLLOW_I_STATEMENT_FORMAT10x_in_instruction1863); 

                    match(input, Token.DOWN, null); 
                    INSTRUCTION_FORMAT10x129=(CommonTree)match(input,INSTRUCTION_FORMAT10x,FOLLOW_INSTRUCTION_FORMAT10x_in_instruction1865); 

                    match(input, Token.UP, null); 

                    			Opcode opcode = Opcode.getOpcodeByName((INSTRUCTION_FORMAT10x129!=null?INSTRUCTION_FORMAT10x129.getText():null));
                    			instructions.add(new Instruction10x(opcode));
                    		

                    }
                    break;
                case 3 :
                    // org/jf/smali/smaliTreeWalker.g:935:3: ^( I_STATEMENT_FORMAT11n INSTRUCTION_FORMAT11n REGISTER short_integral_literal )
                    {
                    match(input,I_STATEMENT_FORMAT11n,FOLLOW_I_STATEMENT_FORMAT11n_in_instruction1879); 

                    match(input, Token.DOWN, null); 
                    INSTRUCTION_FORMAT11n130=(CommonTree)match(input,INSTRUCTION_FORMAT11n,FOLLOW_INSTRUCTION_FORMAT11n_in_instruction1881); 
                    REGISTER131=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_instruction1883); 
                    pushFollow(FOLLOW_short_integral_literal_in_instruction1885);
                    short_integral_literal132=short_integral_literal();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    			Opcode opcode = Opcode.getOpcodeByName((INSTRUCTION_FORMAT11n130!=null?INSTRUCTION_FORMAT11n130.getText():null));
                    			byte regA = parseRegister_nibble((REGISTER131!=null?REGISTER131.getText():null), totalMethodRegisters, methodParameterRegisters);

                    			short litB = short_integral_literal132;
                    			literalTools.checkNibble(litB);

                    			instructions.add(new Instruction11n(opcode, regA, (byte)litB));
                    		

                    }
                    break;
                case 4 :
                    // org/jf/smali/smaliTreeWalker.g:946:3: ^( I_STATEMENT_FORMAT11x INSTRUCTION_FORMAT11x REGISTER )
                    {
                    match(input,I_STATEMENT_FORMAT11x,FOLLOW_I_STATEMENT_FORMAT11x_in_instruction1899); 

                    match(input, Token.DOWN, null); 
                    INSTRUCTION_FORMAT11x133=(CommonTree)match(input,INSTRUCTION_FORMAT11x,FOLLOW_INSTRUCTION_FORMAT11x_in_instruction1901); 
                    REGISTER134=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_instruction1903); 

                    match(input, Token.UP, null); 

                    			Opcode opcode = Opcode.getOpcodeByName((INSTRUCTION_FORMAT11x133!=null?INSTRUCTION_FORMAT11x133.getText():null));
                    			short regA = parseRegister_byte((REGISTER134!=null?REGISTER134.getText():null), totalMethodRegisters, methodParameterRegisters);

                    			instructions.add(new Instruction11x(opcode, regA));
                    		

                    }
                    break;
                case 5 :
                    // org/jf/smali/smaliTreeWalker.g:954:3: ^( I_STATEMENT_FORMAT12x INSTRUCTION_FORMAT12x registerA= REGISTER registerB= REGISTER )
                    {
                    match(input,I_STATEMENT_FORMAT12x,FOLLOW_I_STATEMENT_FORMAT12x_in_instruction1917); 

                    match(input, Token.DOWN, null); 
                    INSTRUCTION_FORMAT12x135=(CommonTree)match(input,INSTRUCTION_FORMAT12x,FOLLOW_INSTRUCTION_FORMAT12x_in_instruction1919); 
                    registerA=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_instruction1923); 
                    registerB=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_instruction1927); 

                    match(input, Token.UP, null); 

                    			Opcode opcode = Opcode.getOpcodeByName((INSTRUCTION_FORMAT12x135!=null?INSTRUCTION_FORMAT12x135.getText():null));
                    			byte regA = parseRegister_nibble((registerA!=null?registerA.getText():null), totalMethodRegisters, methodParameterRegisters);
                    			byte regB = parseRegister_nibble((registerB!=null?registerB.getText():null), totalMethodRegisters, methodParameterRegisters);

                    			instructions.add(new Instruction12x(opcode, regA, regB));
                    		

                    }
                    break;
                case 6 :
                    // org/jf/smali/smaliTreeWalker.g:963:3: ^( I_STATEMENT_FORMAT20t INSTRUCTION_FORMAT20t offset_or_label )
                    {
                    match(input,I_STATEMENT_FORMAT20t,FOLLOW_I_STATEMENT_FORMAT20t_in_instruction1941); 

                    match(input, Token.DOWN, null); 
                    INSTRUCTION_FORMAT20t136=(CommonTree)match(input,INSTRUCTION_FORMAT20t,FOLLOW_INSTRUCTION_FORMAT20t_in_instruction1943); 
                    pushFollow(FOLLOW_offset_or_label_in_instruction1945);
                    offset_or_label137=offset_or_label();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    			Opcode opcode = Opcode.getOpcodeByName((INSTRUCTION_FORMAT20t136!=null?INSTRUCTION_FORMAT20t136.getText():null));

                    			int addressOffset = (offset_or_label137!=null?offset_or_label137.offsetValue:0);

                    			instructions.add(new Instruction20t(opcode, addressOffset));
                    		

                    }
                    break;
                case 7 :
                    // org/jf/smali/smaliTreeWalker.g:972:3: ^( I_STATEMENT_FORMAT21c_FIELD INSTRUCTION_FORMAT21c_FIELD REGISTER fully_qualified_field )
                    {
                    match(input,I_STATEMENT_FORMAT21c_FIELD,FOLLOW_I_STATEMENT_FORMAT21c_FIELD_in_instruction1959); 

                    match(input, Token.DOWN, null); 
                    INSTRUCTION_FORMAT21c_FIELD138=(CommonTree)match(input,INSTRUCTION_FORMAT21c_FIELD,FOLLOW_INSTRUCTION_FORMAT21c_FIELD_in_instruction1961); 
                    REGISTER139=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_instruction1963); 
                    pushFollow(FOLLOW_fully_qualified_field_in_instruction1965);
                    fully_qualified_field140=fully_qualified_field();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    			Opcode opcode = Opcode.getOpcodeByName((INSTRUCTION_FORMAT21c_FIELD138!=null?INSTRUCTION_FORMAT21c_FIELD138.getText():null));
                    			short regA = parseRegister_byte((REGISTER139!=null?REGISTER139.getText():null), totalMethodRegisters, methodParameterRegisters);

                    			FieldIdItem fieldIdItem = fully_qualified_field140;

                    			instructions.add(new Instruction21c(opcode, regA, fieldIdItem));
                    		

                    }
                    break;
                case 8 :
                    // org/jf/smali/smaliTreeWalker.g:982:3: ^( I_STATEMENT_FORMAT21c_STRING INSTRUCTION_FORMAT21c_STRING REGISTER string_literal )
                    {
                    match(input,I_STATEMENT_FORMAT21c_STRING,FOLLOW_I_STATEMENT_FORMAT21c_STRING_in_instruction1979); 

                    match(input, Token.DOWN, null); 
                    INSTRUCTION_FORMAT21c_STRING141=(CommonTree)match(input,INSTRUCTION_FORMAT21c_STRING,FOLLOW_INSTRUCTION_FORMAT21c_STRING_in_instruction1981); 
                    REGISTER142=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_instruction1983); 
                    pushFollow(FOLLOW_string_literal_in_instruction1985);
                    string_literal143=string_literal();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    			Opcode opcode = Opcode.getOpcodeByName((INSTRUCTION_FORMAT21c_STRING141!=null?INSTRUCTION_FORMAT21c_STRING141.getText():null));
                    			short regA = parseRegister_byte((REGISTER142!=null?REGISTER142.getText():null), totalMethodRegisters, methodParameterRegisters);

                    			StringIdItem stringIdItem = StringIdItem.internStringIdItem(dexFile, string_literal143);

                    			instructions.add(new Instruction21c(opcode, regA, stringIdItem));
                    		

                    }
                    break;
                case 9 :
                    // org/jf/smali/smaliTreeWalker.g:992:3: ^( I_STATEMENT_FORMAT21c_TYPE INSTRUCTION_FORMAT21c_TYPE REGISTER reference_type_descriptor )
                    {
                    match(input,I_STATEMENT_FORMAT21c_TYPE,FOLLOW_I_STATEMENT_FORMAT21c_TYPE_in_instruction1999); 

                    match(input, Token.DOWN, null); 
                    INSTRUCTION_FORMAT21c_TYPE144=(CommonTree)match(input,INSTRUCTION_FORMAT21c_TYPE,FOLLOW_INSTRUCTION_FORMAT21c_TYPE_in_instruction2001); 
                    REGISTER145=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_instruction2003); 
                    pushFollow(FOLLOW_reference_type_descriptor_in_instruction2005);
                    reference_type_descriptor146=reference_type_descriptor();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    			Opcode opcode = Opcode.getOpcodeByName((INSTRUCTION_FORMAT21c_TYPE144!=null?INSTRUCTION_FORMAT21c_TYPE144.getText():null));
                    			short regA = parseRegister_byte((REGISTER145!=null?REGISTER145.getText():null), totalMethodRegisters, methodParameterRegisters);

                    			TypeIdItem typeIdItem = (reference_type_descriptor146!=null?reference_type_descriptor146.type:null);

                    			instructions.add(new Instruction21c(opcode, regA, typeIdItem));
                    		

                    }
                    break;
                case 10 :
                    // org/jf/smali/smaliTreeWalker.g:1002:3: ^( I_STATEMENT_FORMAT21h INSTRUCTION_FORMAT21h REGISTER short_integral_literal )
                    {
                    match(input,I_STATEMENT_FORMAT21h,FOLLOW_I_STATEMENT_FORMAT21h_in_instruction2019); 

                    match(input, Token.DOWN, null); 
                    INSTRUCTION_FORMAT21h147=(CommonTree)match(input,INSTRUCTION_FORMAT21h,FOLLOW_INSTRUCTION_FORMAT21h_in_instruction2021); 
                    REGISTER148=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_instruction2023); 
                    pushFollow(FOLLOW_short_integral_literal_in_instruction2025);
                    short_integral_literal149=short_integral_literal();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    			Opcode opcode = Opcode.getOpcodeByName((INSTRUCTION_FORMAT21h147!=null?INSTRUCTION_FORMAT21h147.getText():null));
                    			short regA = parseRegister_byte((REGISTER148!=null?REGISTER148.getText():null), totalMethodRegisters, methodParameterRegisters);

                    			short litB = short_integral_literal149;

                    			instructions.add(new Instruction21h(opcode, regA, litB));
                    		

                    }
                    break;
                case 11 :
                    // org/jf/smali/smaliTreeWalker.g:1012:3: ^( I_STATEMENT_FORMAT21s INSTRUCTION_FORMAT21s REGISTER short_integral_literal )
                    {
                    match(input,I_STATEMENT_FORMAT21s,FOLLOW_I_STATEMENT_FORMAT21s_in_instruction2039); 

                    match(input, Token.DOWN, null); 
                    INSTRUCTION_FORMAT21s150=(CommonTree)match(input,INSTRUCTION_FORMAT21s,FOLLOW_INSTRUCTION_FORMAT21s_in_instruction2041); 
                    REGISTER151=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_instruction2043); 
                    pushFollow(FOLLOW_short_integral_literal_in_instruction2045);
                    short_integral_literal152=short_integral_literal();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    			Opcode opcode = Opcode.getOpcodeByName((INSTRUCTION_FORMAT21s150!=null?INSTRUCTION_FORMAT21s150.getText():null));
                    			short regA = parseRegister_byte((REGISTER151!=null?REGISTER151.getText():null), totalMethodRegisters, methodParameterRegisters);

                    			short litB = short_integral_literal152;

                    			instructions.add(new Instruction21s(opcode, regA, litB));
                    		

                    }
                    break;
                case 12 :
                    // org/jf/smali/smaliTreeWalker.g:1022:3: ^( I_STATEMENT_FORMAT21t INSTRUCTION_FORMAT21t REGISTER offset_or_label )
                    {
                    match(input,I_STATEMENT_FORMAT21t,FOLLOW_I_STATEMENT_FORMAT21t_in_instruction2059); 

                    match(input, Token.DOWN, null); 
                    INSTRUCTION_FORMAT21t153=(CommonTree)match(input,INSTRUCTION_FORMAT21t,FOLLOW_INSTRUCTION_FORMAT21t_in_instruction2061); 
                    REGISTER154=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_instruction2063); 
                    pushFollow(FOLLOW_offset_or_label_in_instruction2065);
                    offset_or_label155=offset_or_label();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    			Opcode opcode = Opcode.getOpcodeByName((INSTRUCTION_FORMAT21t153!=null?INSTRUCTION_FORMAT21t153.getText():null));
                    			short regA = parseRegister_byte((REGISTER154!=null?REGISTER154.getText():null), totalMethodRegisters, methodParameterRegisters);

                    			int addressOffset = (offset_or_label155!=null?offset_or_label155.offsetValue:0);

                    			if (addressOffset < Short.MIN_VALUE || addressOffset > Short.MAX_VALUE) {
                    				throw new SemanticException(input, (offset_or_label155!=null?((CommonTree)offset_or_label155.start):null), "The offset/label is out of range. The offset is " + Integer.toString(addressOffset) + " and the range for this opcode is [-32768, 32767].");
                    			}

                    			instructions.add(new Instruction21t(opcode, regA, (short)addressOffset));
                    		

                    }
                    break;
                case 13 :
                    // org/jf/smali/smaliTreeWalker.g:1036:3: ^( I_STATEMENT_FORMAT22b INSTRUCTION_FORMAT22b registerA= REGISTER registerB= REGISTER short_integral_literal )
                    {
                    match(input,I_STATEMENT_FORMAT22b,FOLLOW_I_STATEMENT_FORMAT22b_in_instruction2079); 

                    match(input, Token.DOWN, null); 
                    INSTRUCTION_FORMAT22b156=(CommonTree)match(input,INSTRUCTION_FORMAT22b,FOLLOW_INSTRUCTION_FORMAT22b_in_instruction2081); 
                    registerA=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_instruction2085); 
                    registerB=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_instruction2089); 
                    pushFollow(FOLLOW_short_integral_literal_in_instruction2091);
                    short_integral_literal157=short_integral_literal();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    			Opcode opcode = Opcode.getOpcodeByName((INSTRUCTION_FORMAT22b156!=null?INSTRUCTION_FORMAT22b156.getText():null));
                    			short regA = parseRegister_byte((registerA!=null?registerA.getText():null), totalMethodRegisters, methodParameterRegisters);
                    			short regB = parseRegister_byte((registerB!=null?registerB.getText():null), totalMethodRegisters, methodParameterRegisters);

                    			short litC = short_integral_literal157;
                    			literalTools.checkByte(litC);

                    			instructions.add(new Instruction22b(opcode, regA, regB, (byte)litC));
                    		

                    }
                    break;
                case 14 :
                    // org/jf/smali/smaliTreeWalker.g:1048:3: ^( I_STATEMENT_FORMAT22c_FIELD INSTRUCTION_FORMAT22c_FIELD registerA= REGISTER registerB= REGISTER fully_qualified_field )
                    {
                    match(input,I_STATEMENT_FORMAT22c_FIELD,FOLLOW_I_STATEMENT_FORMAT22c_FIELD_in_instruction2105); 

                    match(input, Token.DOWN, null); 
                    INSTRUCTION_FORMAT22c_FIELD158=(CommonTree)match(input,INSTRUCTION_FORMAT22c_FIELD,FOLLOW_INSTRUCTION_FORMAT22c_FIELD_in_instruction2107); 
                    registerA=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_instruction2111); 
                    registerB=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_instruction2115); 
                    pushFollow(FOLLOW_fully_qualified_field_in_instruction2117);
                    fully_qualified_field159=fully_qualified_field();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    			Opcode opcode = Opcode.getOpcodeByName((INSTRUCTION_FORMAT22c_FIELD158!=null?INSTRUCTION_FORMAT22c_FIELD158.getText():null));
                    			byte regA = parseRegister_nibble((registerA!=null?registerA.getText():null), totalMethodRegisters, methodParameterRegisters);
                    			byte regB = parseRegister_nibble((registerB!=null?registerB.getText():null), totalMethodRegisters, methodParameterRegisters);

                    			FieldIdItem fieldIdItem = fully_qualified_field159;

                    			instructions.add(new Instruction22c(opcode, regA, regB, fieldIdItem));
                    		

                    }
                    break;
                case 15 :
                    // org/jf/smali/smaliTreeWalker.g:1059:3: ^( I_STATEMENT_FORMAT22c_TYPE INSTRUCTION_FORMAT22c_TYPE registerA= REGISTER registerB= REGISTER nonvoid_type_descriptor )
                    {
                    match(input,I_STATEMENT_FORMAT22c_TYPE,FOLLOW_I_STATEMENT_FORMAT22c_TYPE_in_instruction2131); 

                    match(input, Token.DOWN, null); 
                    INSTRUCTION_FORMAT22c_TYPE160=(CommonTree)match(input,INSTRUCTION_FORMAT22c_TYPE,FOLLOW_INSTRUCTION_FORMAT22c_TYPE_in_instruction2133); 
                    registerA=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_instruction2137); 
                    registerB=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_instruction2141); 
                    pushFollow(FOLLOW_nonvoid_type_descriptor_in_instruction2143);
                    nonvoid_type_descriptor161=nonvoid_type_descriptor();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    			Opcode opcode = Opcode.getOpcodeByName((INSTRUCTION_FORMAT22c_TYPE160!=null?INSTRUCTION_FORMAT22c_TYPE160.getText():null));
                    			byte regA = parseRegister_nibble((registerA!=null?registerA.getText():null), totalMethodRegisters, methodParameterRegisters);
                    			byte regB = parseRegister_nibble((registerB!=null?registerB.getText():null), totalMethodRegisters, methodParameterRegisters);

                    			TypeIdItem typeIdItem = (nonvoid_type_descriptor161!=null?nonvoid_type_descriptor161.type:null);

                    			instructions.add(new Instruction22c(opcode, regA, regB, typeIdItem));
                    		

                    }
                    break;
                case 16 :
                    // org/jf/smali/smaliTreeWalker.g:1070:3: ^( I_STATEMENT_FORMAT22s INSTRUCTION_FORMAT22s registerA= REGISTER registerB= REGISTER short_integral_literal )
                    {
                    match(input,I_STATEMENT_FORMAT22s,FOLLOW_I_STATEMENT_FORMAT22s_in_instruction2157); 

                    match(input, Token.DOWN, null); 
                    INSTRUCTION_FORMAT22s162=(CommonTree)match(input,INSTRUCTION_FORMAT22s,FOLLOW_INSTRUCTION_FORMAT22s_in_instruction2159); 
                    registerA=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_instruction2163); 
                    registerB=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_instruction2167); 
                    pushFollow(FOLLOW_short_integral_literal_in_instruction2169);
                    short_integral_literal163=short_integral_literal();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    			Opcode opcode = Opcode.getOpcodeByName((INSTRUCTION_FORMAT22s162!=null?INSTRUCTION_FORMAT22s162.getText():null));
                    			byte regA = parseRegister_nibble((registerA!=null?registerA.getText():null), totalMethodRegisters, methodParameterRegisters);
                    			byte regB = parseRegister_nibble((registerB!=null?registerB.getText():null), totalMethodRegisters, methodParameterRegisters);

                    			short litC = short_integral_literal163;

                    			instructions.add(new Instruction22s(opcode, regA, regB, litC));
                    		

                    }
                    break;
                case 17 :
                    // org/jf/smali/smaliTreeWalker.g:1081:3: ^( I_STATEMENT_FORMAT22t INSTRUCTION_FORMAT22t registerA= REGISTER registerB= REGISTER offset_or_label )
                    {
                    match(input,I_STATEMENT_FORMAT22t,FOLLOW_I_STATEMENT_FORMAT22t_in_instruction2183); 

                    match(input, Token.DOWN, null); 
                    INSTRUCTION_FORMAT22t164=(CommonTree)match(input,INSTRUCTION_FORMAT22t,FOLLOW_INSTRUCTION_FORMAT22t_in_instruction2185); 
                    registerA=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_instruction2189); 
                    registerB=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_instruction2193); 
                    pushFollow(FOLLOW_offset_or_label_in_instruction2195);
                    offset_or_label165=offset_or_label();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    			Opcode opcode = Opcode.getOpcodeByName((INSTRUCTION_FORMAT22t164!=null?INSTRUCTION_FORMAT22t164.getText():null));
                    			byte regA = parseRegister_nibble((registerA!=null?registerA.getText():null), totalMethodRegisters, methodParameterRegisters);
                    			byte regB = parseRegister_nibble((registerB!=null?registerB.getText():null), totalMethodRegisters, methodParameterRegisters);

                    			int addressOffset = (offset_or_label165!=null?offset_or_label165.offsetValue:0);

                    			if (addressOffset < Short.MIN_VALUE || addressOffset > Short.MAX_VALUE) {
                    				throw new SemanticException(input, (offset_or_label165!=null?((CommonTree)offset_or_label165.start):null), "The offset/label is out of range. The offset is " + Integer.toString(addressOffset) + " and the range for this opcode is [-32768, 32767].");
                    			}

                    			instructions.add(new Instruction22t(opcode, regA, regB, (short)addressOffset));
                    		

                    }
                    break;
                case 18 :
                    // org/jf/smali/smaliTreeWalker.g:1096:3: ^( I_STATEMENT_FORMAT22x INSTRUCTION_FORMAT22x registerA= REGISTER registerB= REGISTER )
                    {
                    match(input,I_STATEMENT_FORMAT22x,FOLLOW_I_STATEMENT_FORMAT22x_in_instruction2209); 

                    match(input, Token.DOWN, null); 
                    INSTRUCTION_FORMAT22x166=(CommonTree)match(input,INSTRUCTION_FORMAT22x,FOLLOW_INSTRUCTION_FORMAT22x_in_instruction2211); 
                    registerA=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_instruction2215); 
                    registerB=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_instruction2219); 

                    match(input, Token.UP, null); 

                    			Opcode opcode = Opcode.getOpcodeByName((INSTRUCTION_FORMAT22x166!=null?INSTRUCTION_FORMAT22x166.getText():null));
                    			short regA = parseRegister_byte((registerA!=null?registerA.getText():null), totalMethodRegisters, methodParameterRegisters);
                    			int regB = parseRegister_short((registerB!=null?registerB.getText():null), totalMethodRegisters, methodParameterRegisters);

                    			instructions.add(new Instruction22x(opcode, regA, regB));
                    		

                    }
                    break;
                case 19 :
                    // org/jf/smali/smaliTreeWalker.g:1105:3: ^( I_STATEMENT_FORMAT23x INSTRUCTION_FORMAT23x registerA= REGISTER registerB= REGISTER registerC= REGISTER )
                    {
                    match(input,I_STATEMENT_FORMAT23x,FOLLOW_I_STATEMENT_FORMAT23x_in_instruction2233); 

                    match(input, Token.DOWN, null); 
                    INSTRUCTION_FORMAT23x167=(CommonTree)match(input,INSTRUCTION_FORMAT23x,FOLLOW_INSTRUCTION_FORMAT23x_in_instruction2235); 
                    registerA=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_instruction2239); 
                    registerB=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_instruction2243); 
                    registerC=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_instruction2247); 

                    match(input, Token.UP, null); 

                    			Opcode opcode = Opcode.getOpcodeByName((INSTRUCTION_FORMAT23x167!=null?INSTRUCTION_FORMAT23x167.getText():null));
                    			short regA = parseRegister_byte((registerA!=null?registerA.getText():null), totalMethodRegisters, methodParameterRegisters);
                    			short regB = parseRegister_byte((registerB!=null?registerB.getText():null), totalMethodRegisters, methodParameterRegisters);
                    			short regC = parseRegister_byte((registerC!=null?registerC.getText():null), totalMethodRegisters, methodParameterRegisters);

                    			instructions.add(new Instruction23x(opcode, regA, regB, regC));
                    		

                    }
                    break;
                case 20 :
                    // org/jf/smali/smaliTreeWalker.g:1115:3: ^( I_STATEMENT_FORMAT30t INSTRUCTION_FORMAT30t offset_or_label )
                    {
                    match(input,I_STATEMENT_FORMAT30t,FOLLOW_I_STATEMENT_FORMAT30t_in_instruction2261); 

                    match(input, Token.DOWN, null); 
                    INSTRUCTION_FORMAT30t168=(CommonTree)match(input,INSTRUCTION_FORMAT30t,FOLLOW_INSTRUCTION_FORMAT30t_in_instruction2263); 
                    pushFollow(FOLLOW_offset_or_label_in_instruction2265);
                    offset_or_label169=offset_or_label();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    			Opcode opcode = Opcode.getOpcodeByName((INSTRUCTION_FORMAT30t168!=null?INSTRUCTION_FORMAT30t168.getText():null));

                    			int addressOffset = (offset_or_label169!=null?offset_or_label169.offsetValue:0);

                    			instructions.add(new Instruction30t(opcode, addressOffset));
                    		

                    }
                    break;
                case 21 :
                    // org/jf/smali/smaliTreeWalker.g:1124:3: ^( I_STATEMENT_FORMAT31c INSTRUCTION_FORMAT31c REGISTER string_literal )
                    {
                    match(input,I_STATEMENT_FORMAT31c,FOLLOW_I_STATEMENT_FORMAT31c_in_instruction2279); 

                    match(input, Token.DOWN, null); 
                    INSTRUCTION_FORMAT31c170=(CommonTree)match(input,INSTRUCTION_FORMAT31c,FOLLOW_INSTRUCTION_FORMAT31c_in_instruction2281); 
                    REGISTER171=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_instruction2283); 
                    pushFollow(FOLLOW_string_literal_in_instruction2285);
                    string_literal172=string_literal();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    			Opcode opcode = Opcode.getOpcodeByName((INSTRUCTION_FORMAT31c170!=null?INSTRUCTION_FORMAT31c170.getText():null));
                    			short regA = parseRegister_byte((REGISTER171!=null?REGISTER171.getText():null), totalMethodRegisters, methodParameterRegisters);

                    			StringIdItem stringIdItem = StringIdItem.internStringIdItem(dexFile, string_literal172);

                    			instructions.add(new Instruction31c(opcode, regA, stringIdItem));
                    		

                    }
                    break;
                case 22 :
                    // org/jf/smali/smaliTreeWalker.g:1134:3: ^( I_STATEMENT_FORMAT31i INSTRUCTION_FORMAT31i REGISTER fixed_32bit_literal )
                    {
                    match(input,I_STATEMENT_FORMAT31i,FOLLOW_I_STATEMENT_FORMAT31i_in_instruction2299); 

                    match(input, Token.DOWN, null); 
                    INSTRUCTION_FORMAT31i173=(CommonTree)match(input,INSTRUCTION_FORMAT31i,FOLLOW_INSTRUCTION_FORMAT31i_in_instruction2301); 
                    REGISTER174=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_instruction2303); 
                    pushFollow(FOLLOW_fixed_32bit_literal_in_instruction2305);
                    fixed_32bit_literal175=fixed_32bit_literal();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    			Opcode opcode = Opcode.getOpcodeByName((INSTRUCTION_FORMAT31i173!=null?INSTRUCTION_FORMAT31i173.getText():null));
                    			short regA = parseRegister_byte((REGISTER174!=null?REGISTER174.getText():null), totalMethodRegisters, methodParameterRegisters);

                    			int litB = fixed_32bit_literal175;

                    			instructions.add(new Instruction31i(opcode, regA, litB));
                    		

                    }
                    break;
                case 23 :
                    // org/jf/smali/smaliTreeWalker.g:1144:3: ^( I_STATEMENT_FORMAT31t INSTRUCTION_FORMAT31t REGISTER offset_or_label )
                    {
                    match(input,I_STATEMENT_FORMAT31t,FOLLOW_I_STATEMENT_FORMAT31t_in_instruction2319); 

                    match(input, Token.DOWN, null); 
                    INSTRUCTION_FORMAT31t176=(CommonTree)match(input,INSTRUCTION_FORMAT31t,FOLLOW_INSTRUCTION_FORMAT31t_in_instruction2321); 
                    REGISTER177=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_instruction2323); 
                    pushFollow(FOLLOW_offset_or_label_in_instruction2325);
                    offset_or_label178=offset_or_label();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    			Opcode opcode = Opcode.getOpcodeByName((INSTRUCTION_FORMAT31t176!=null?INSTRUCTION_FORMAT31t176.getText():null));

                    			short regA = parseRegister_byte((REGISTER177!=null?REGISTER177.getText():null), totalMethodRegisters, methodParameterRegisters);

                    			int addressOffset = (offset_or_label178!=null?offset_or_label178.offsetValue:0);
                    			if ((((method_scope)method_stack.peek()).currentAddress + addressOffset) % 2 != 0) {
                    				addressOffset++;
                    			}

                    			instructions.add(new Instruction31t(opcode, regA, addressOffset));
                    		

                    }
                    break;
                case 24 :
                    // org/jf/smali/smaliTreeWalker.g:1158:3: ^( I_STATEMENT_FORMAT32x INSTRUCTION_FORMAT32x registerA= REGISTER registerB= REGISTER )
                    {
                    match(input,I_STATEMENT_FORMAT32x,FOLLOW_I_STATEMENT_FORMAT32x_in_instruction2339); 

                    match(input, Token.DOWN, null); 
                    INSTRUCTION_FORMAT32x179=(CommonTree)match(input,INSTRUCTION_FORMAT32x,FOLLOW_INSTRUCTION_FORMAT32x_in_instruction2341); 
                    registerA=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_instruction2345); 
                    registerB=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_instruction2349); 

                    match(input, Token.UP, null); 

                    			Opcode opcode = Opcode.getOpcodeByName((INSTRUCTION_FORMAT32x179!=null?INSTRUCTION_FORMAT32x179.getText():null));
                    			int regA = parseRegister_short((registerA!=null?registerA.getText():null), totalMethodRegisters, methodParameterRegisters);
                    			int regB = parseRegister_short((registerB!=null?registerB.getText():null), totalMethodRegisters, methodParameterRegisters);

                    			instructions.add(new Instruction32x(opcode, regA, regB));
                    		

                    }
                    break;
                case 25 :
                    // org/jf/smali/smaliTreeWalker.g:1167:3: ^( I_STATEMENT_FORMAT35c_METHOD INSTRUCTION_FORMAT35c_METHOD register_list[$totalMethodRegisters, $methodParameterRegisters] fully_qualified_method )
                    {
                    match(input,I_STATEMENT_FORMAT35c_METHOD,FOLLOW_I_STATEMENT_FORMAT35c_METHOD_in_instruction2363); 

                    match(input, Token.DOWN, null); 
                    INSTRUCTION_FORMAT35c_METHOD180=(CommonTree)match(input,INSTRUCTION_FORMAT35c_METHOD,FOLLOW_INSTRUCTION_FORMAT35c_METHOD_in_instruction2365); 
                    pushFollow(FOLLOW_register_list_in_instruction2367);
                    register_list181=register_list(totalMethodRegisters, methodParameterRegisters);

                    state._fsp--;

                    pushFollow(FOLLOW_fully_qualified_method_in_instruction2370);
                    fully_qualified_method182=fully_qualified_method();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    			Opcode opcode = Opcode.getOpcodeByName((INSTRUCTION_FORMAT35c_METHOD180!=null?INSTRUCTION_FORMAT35c_METHOD180.getText():null));

                    			//this depends on the fact that register_list returns a byte[5]
                    			byte[] registers = (register_list181!=null?register_list181.registers:null);
                    			byte registerCount = (register_list181!=null?register_list181.registerCount:0);
                    			outRegisters = registerCount;

                    			MethodIdItem methodIdItem = fully_qualified_method182;

                    			instructions.add(new Instruction35c(opcode, registerCount, registers[0], registers[1], registers[2], registers[3], registers[4], methodIdItem));
                    		

                    }
                    break;
                case 26 :
                    // org/jf/smali/smaliTreeWalker.g:1181:3: ^( I_STATEMENT_FORMAT35c_TYPE INSTRUCTION_FORMAT35c_TYPE register_list[$totalMethodRegisters, $methodParameterRegisters] nonvoid_type_descriptor )
                    {
                    match(input,I_STATEMENT_FORMAT35c_TYPE,FOLLOW_I_STATEMENT_FORMAT35c_TYPE_in_instruction2384); 

                    match(input, Token.DOWN, null); 
                    INSTRUCTION_FORMAT35c_TYPE183=(CommonTree)match(input,INSTRUCTION_FORMAT35c_TYPE,FOLLOW_INSTRUCTION_FORMAT35c_TYPE_in_instruction2386); 
                    pushFollow(FOLLOW_register_list_in_instruction2388);
                    register_list184=register_list(totalMethodRegisters, methodParameterRegisters);

                    state._fsp--;

                    pushFollow(FOLLOW_nonvoid_type_descriptor_in_instruction2391);
                    nonvoid_type_descriptor185=nonvoid_type_descriptor();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    			Opcode opcode = Opcode.getOpcodeByName((INSTRUCTION_FORMAT35c_TYPE183!=null?INSTRUCTION_FORMAT35c_TYPE183.getText():null));

                    			//this depends on the fact that register_list returns a byte[5]
                    			byte[] registers = (register_list184!=null?register_list184.registers:null);
                    			byte registerCount = (register_list184!=null?register_list184.registerCount:0);
                    			outRegisters = registerCount;

                    			TypeIdItem typeIdItem = (nonvoid_type_descriptor185!=null?nonvoid_type_descriptor185.type:null);

                    			instructions.add(new Instruction35c(opcode, registerCount, registers[0], registers[1], registers[2], registers[3], registers[4], typeIdItem));
                    		

                    }
                    break;
                case 27 :
                    // org/jf/smali/smaliTreeWalker.g:1195:3: ^( I_STATEMENT_FORMAT3rc_METHOD INSTRUCTION_FORMAT3rc_METHOD register_range[$totalMethodRegisters, $methodParameterRegisters] fully_qualified_method )
                    {
                    match(input,I_STATEMENT_FORMAT3rc_METHOD,FOLLOW_I_STATEMENT_FORMAT3rc_METHOD_in_instruction2405); 

                    match(input, Token.DOWN, null); 
                    INSTRUCTION_FORMAT3rc_METHOD186=(CommonTree)match(input,INSTRUCTION_FORMAT3rc_METHOD,FOLLOW_INSTRUCTION_FORMAT3rc_METHOD_in_instruction2407); 
                    pushFollow(FOLLOW_register_range_in_instruction2409);
                    register_range187=register_range(totalMethodRegisters, methodParameterRegisters);

                    state._fsp--;

                    pushFollow(FOLLOW_fully_qualified_method_in_instruction2412);
                    fully_qualified_method188=fully_qualified_method();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    			Opcode opcode = Opcode.getOpcodeByName((INSTRUCTION_FORMAT3rc_METHOD186!=null?INSTRUCTION_FORMAT3rc_METHOD186.getText():null));
                    			int startRegister = (register_range187!=null?register_range187.startRegister:0);
                    			int endRegister = (register_range187!=null?register_range187.endRegister:0);

                    			int registerCount = endRegister-startRegister+1;
                    			outRegisters = registerCount;

                    			MethodIdItem methodIdItem = fully_qualified_method188;

                    			instructions.add(new Instruction3rc(opcode, (short)registerCount, startRegister, methodIdItem));
                    		

                    }
                    break;
                case 28 :
                    // org/jf/smali/smaliTreeWalker.g:1209:3: ^( I_STATEMENT_FORMAT3rc_TYPE INSTRUCTION_FORMAT3rc_TYPE register_range[$totalMethodRegisters, $methodParameterRegisters] nonvoid_type_descriptor )
                    {
                    match(input,I_STATEMENT_FORMAT3rc_TYPE,FOLLOW_I_STATEMENT_FORMAT3rc_TYPE_in_instruction2426); 

                    match(input, Token.DOWN, null); 
                    INSTRUCTION_FORMAT3rc_TYPE189=(CommonTree)match(input,INSTRUCTION_FORMAT3rc_TYPE,FOLLOW_INSTRUCTION_FORMAT3rc_TYPE_in_instruction2428); 
                    pushFollow(FOLLOW_register_range_in_instruction2430);
                    register_range190=register_range(totalMethodRegisters, methodParameterRegisters);

                    state._fsp--;

                    pushFollow(FOLLOW_nonvoid_type_descriptor_in_instruction2433);
                    nonvoid_type_descriptor191=nonvoid_type_descriptor();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    			Opcode opcode = Opcode.getOpcodeByName((INSTRUCTION_FORMAT3rc_TYPE189!=null?INSTRUCTION_FORMAT3rc_TYPE189.getText():null));
                    			int startRegister = (register_range190!=null?register_range190.startRegister:0);
                    			int endRegister = (register_range190!=null?register_range190.endRegister:0);

                    			int registerCount = endRegister-startRegister+1;
                    			outRegisters = registerCount;

                    			TypeIdItem typeIdItem = (nonvoid_type_descriptor191!=null?nonvoid_type_descriptor191.type:null);

                    			instructions.add(new Instruction3rc(opcode, (short)registerCount, startRegister, typeIdItem));
                    		

                    }
                    break;
                case 29 :
                    // org/jf/smali/smaliTreeWalker.g:1223:3: ^( I_STATEMENT_FORMAT51l INSTRUCTION_FORMAT51l REGISTER fixed_64bit_literal )
                    {
                    match(input,I_STATEMENT_FORMAT51l,FOLLOW_I_STATEMENT_FORMAT51l_in_instruction2447); 

                    match(input, Token.DOWN, null); 
                    INSTRUCTION_FORMAT51l192=(CommonTree)match(input,INSTRUCTION_FORMAT51l,FOLLOW_INSTRUCTION_FORMAT51l_in_instruction2449); 
                    REGISTER193=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_instruction2451); 
                    pushFollow(FOLLOW_fixed_64bit_literal_in_instruction2453);
                    fixed_64bit_literal194=fixed_64bit_literal();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    			Opcode opcode = Opcode.getOpcodeByName((INSTRUCTION_FORMAT51l192!=null?INSTRUCTION_FORMAT51l192.getText():null));
                    			short regA = parseRegister_byte((REGISTER193!=null?REGISTER193.getText():null), totalMethodRegisters, methodParameterRegisters);

                    			long litB = fixed_64bit_literal194;

                    			instructions.add(new Instruction51l(opcode, regA, litB));
                    		

                    }
                    break;
                case 30 :
                    // org/jf/smali/smaliTreeWalker.g:1233:3: ^( I_STATEMENT_ARRAY_DATA ^( I_ARRAY_ELEMENT_SIZE short_integral_literal ) array_elements )
                    {
                    match(input,I_STATEMENT_ARRAY_DATA,FOLLOW_I_STATEMENT_ARRAY_DATA_in_instruction2467); 

                    match(input, Token.DOWN, null); 
                    match(input,I_ARRAY_ELEMENT_SIZE,FOLLOW_I_ARRAY_ELEMENT_SIZE_in_instruction2470); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_short_integral_literal_in_instruction2472);
                    short_integral_literal195=short_integral_literal();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    pushFollow(FOLLOW_array_elements_in_instruction2475);
                    array_elements196=array_elements();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    			if ((((method_scope)method_stack.peek()).currentAddress % 2) != 0) {
                    				instructions.add(new Instruction10x(Opcode.NOP));
                    				((method_scope)method_stack.peek()).currentAddress++;
                    			}

                    			int elementWidth = short_integral_literal195;
                    			List<byte[]> byteValues = array_elements196;

                    			int length = 0;
                    			for (byte[] byteValue: byteValues) {
                    				length+=byteValue.length;
                    			}

                    			byte[] encodedValues = new byte[length];
                    			int index = 0;
                    			for (byte[] byteValue: byteValues) {
                    				System.arraycopy(byteValue, 0, encodedValues, index, byteValue.length);
                    				index+=byteValue.length;
                    			}

                    			instructions.add(new ArrayDataPseudoInstruction(elementWidth, encodedValues));
                    		

                    }
                    break;
                case 31 :
                    // org/jf/smali/smaliTreeWalker.g:1259:3: ^( I_STATEMENT_PACKED_SWITCH ^( I_PACKED_SWITCH_START_KEY fixed_32bit_literal ) packed_switch_targets[baseAddress] )
                    {
                    match(input,I_STATEMENT_PACKED_SWITCH,FOLLOW_I_STATEMENT_PACKED_SWITCH_in_instruction2489); 

                    match(input, Token.DOWN, null); 
                    match(input,I_PACKED_SWITCH_START_KEY,FOLLOW_I_PACKED_SWITCH_START_KEY_in_instruction2492); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_fixed_32bit_literal_in_instruction2494);
                    fixed_32bit_literal197=fixed_32bit_literal();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    				if ((((method_scope)method_stack.peek()).currentAddress % 2) != 0) {
                    					instructions.add(new Instruction10x(Opcode.NOP));
                    					((method_scope)method_stack.peek()).currentAddress++;
                    				}
                    				Integer baseAddress = ((method_scope)method_stack.peek()).packedSwitchDeclarations.get(((method_scope)method_stack.peek()).currentAddress);
                    				if (baseAddress == null) {
                    					baseAddress = 0;
                    				}
                    			
                    pushFollow(FOLLOW_packed_switch_targets_in_instruction2505);
                    packed_switch_targets198=packed_switch_targets(baseAddress);

                    state._fsp--;


                    match(input, Token.UP, null); 


                    			int startKey = fixed_32bit_literal197;
                    			int[] targets = packed_switch_targets198;

                    			instructions.add(new PackedSwitchDataPseudoInstruction(startKey, targets));
                    		

                    }
                    break;
                case 32 :
                    // org/jf/smali/smaliTreeWalker.g:1279:3: ^( I_STATEMENT_SPARSE_SWITCH sparse_switch_target_count sparse_switch_keys[$sparse_switch_target_count.targetCount] sparse_switch_targets[baseAddress, $sparse_switch_target_count.targetCount] )
                    {
                    match(input,I_STATEMENT_SPARSE_SWITCH,FOLLOW_I_STATEMENT_SPARSE_SWITCH_in_instruction2519); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_sparse_switch_target_count_in_instruction2521);
                    sparse_switch_target_count199=sparse_switch_target_count();

                    state._fsp--;

                    pushFollow(FOLLOW_sparse_switch_keys_in_instruction2523);
                    sparse_switch_keys200=sparse_switch_keys(sparse_switch_target_count199);

                    state._fsp--;


                    				if ((((method_scope)method_stack.peek()).currentAddress % 2) != 0) {
                    					instructions.add(new Instruction10x(Opcode.NOP));
                    					((method_scope)method_stack.peek()).currentAddress++;
                    				}
                    				Integer baseAddress = ((method_scope)method_stack.peek()).sparseSwitchDeclarations.get(((method_scope)method_stack.peek()).currentAddress);
                    				if (baseAddress == null) {
                    					baseAddress = 0;
                    				}
                    			
                    pushFollow(FOLLOW_sparse_switch_targets_in_instruction2535);
                    sparse_switch_targets201=sparse_switch_targets(baseAddress, sparse_switch_target_count199);

                    state._fsp--;


                    match(input, Token.UP, null); 

                    			int[] keys = sparse_switch_keys200;
                    			int[] targets = sparse_switch_targets201;

                    			instructions.add(new SparseSwitchDataPseudoInstruction(keys, targets));
                    		

                    }
                    break;

            }
        }
        catch (Exception ex) {

            			reportError(new SemanticException(input, ex));
            			recover(input, null);
            		
        }
        finally {
        }
        return outRegisters;
    }
    // $ANTLR end "instruction"

    public static class nonvoid_type_descriptor_return extends TreeRuleReturnScope {
        public TypeIdItem type;
    };

    // $ANTLR start "nonvoid_type_descriptor"
    // org/jf/smali/smaliTreeWalker.g:1303:1: nonvoid_type_descriptor returns [TypeIdItem type] : ( PRIMITIVE_TYPE | CLASS_DESCRIPTOR | ARRAY_DESCRIPTOR ) ;
    public final smaliTreeWalker.nonvoid_type_descriptor_return nonvoid_type_descriptor() throws RecognitionException {
        smaliTreeWalker.nonvoid_type_descriptor_return retval = new smaliTreeWalker.nonvoid_type_descriptor_return();
        retval.start = input.LT(1);

        try {
            // org/jf/smali/smaliTreeWalker.g:1304:2: ( ( PRIMITIVE_TYPE | CLASS_DESCRIPTOR | ARRAY_DESCRIPTOR ) )
            // org/jf/smali/smaliTreeWalker.g:1304:4: ( PRIMITIVE_TYPE | CLASS_DESCRIPTOR | ARRAY_DESCRIPTOR )
            {
            if ( input.LA(1)==PRIMITIVE_TYPE||(input.LA(1)>=CLASS_DESCRIPTOR && input.LA(1)<=ARRAY_DESCRIPTOR) ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            		retval.type = TypeIdItem.internTypeIdItem(dexFile, ((CommonTree)retval.start).getText());
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "nonvoid_type_descriptor"

    public static class reference_type_descriptor_return extends TreeRuleReturnScope {
        public TypeIdItem type;
    };

    // $ANTLR start "reference_type_descriptor"
    // org/jf/smali/smaliTreeWalker.g:1312:1: reference_type_descriptor returns [TypeIdItem type] : ( CLASS_DESCRIPTOR | ARRAY_DESCRIPTOR ) ;
    public final smaliTreeWalker.reference_type_descriptor_return reference_type_descriptor() throws RecognitionException {
        smaliTreeWalker.reference_type_descriptor_return retval = new smaliTreeWalker.reference_type_descriptor_return();
        retval.start = input.LT(1);

        try {
            // org/jf/smali/smaliTreeWalker.g:1313:2: ( ( CLASS_DESCRIPTOR | ARRAY_DESCRIPTOR ) )
            // org/jf/smali/smaliTreeWalker.g:1313:4: ( CLASS_DESCRIPTOR | ARRAY_DESCRIPTOR )
            {
            if ( (input.LA(1)>=CLASS_DESCRIPTOR && input.LA(1)<=ARRAY_DESCRIPTOR) ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            		retval.type = TypeIdItem.internTypeIdItem(dexFile, ((CommonTree)retval.start).getText());
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "reference_type_descriptor"


    // $ANTLR start "class_type_descriptor"
    // org/jf/smali/smaliTreeWalker.g:1324:1: class_type_descriptor returns [TypeIdItem type] : CLASS_DESCRIPTOR ;
    public final TypeIdItem class_type_descriptor() throws RecognitionException {
        TypeIdItem type = null;

        CommonTree CLASS_DESCRIPTOR202=null;

        try {
            // org/jf/smali/smaliTreeWalker.g:1325:2: ( CLASS_DESCRIPTOR )
            // org/jf/smali/smaliTreeWalker.g:1325:4: CLASS_DESCRIPTOR
            {
            CLASS_DESCRIPTOR202=(CommonTree)match(input,CLASS_DESCRIPTOR,FOLLOW_CLASS_DESCRIPTOR_in_class_type_descriptor2619); 

            		type = TypeIdItem.internTypeIdItem(dexFile, (CLASS_DESCRIPTOR202!=null?CLASS_DESCRIPTOR202.getText():null));
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return type;
    }
    // $ANTLR end "class_type_descriptor"


    // $ANTLR start "type_descriptor"
    // org/jf/smali/smaliTreeWalker.g:1330:1: type_descriptor returns [TypeIdItem type] : ( VOID_TYPE | nonvoid_type_descriptor );
    public final TypeIdItem type_descriptor() throws RecognitionException {
        TypeIdItem type = null;

        smaliTreeWalker.nonvoid_type_descriptor_return nonvoid_type_descriptor203 = null;


        try {
            // org/jf/smali/smaliTreeWalker.g:1331:2: ( VOID_TYPE | nonvoid_type_descriptor )
            int alt35=2;
            switch ( input.LA(1) ) {
            case VOID_TYPE:
                {
                alt35=1;
                }
                break;
            case PRIMITIVE_TYPE:
            case CLASS_DESCRIPTOR:
            case ARRAY_DESCRIPTOR:
                {
                alt35=2;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 35, 0, input);

                throw nvae;
            }

            switch (alt35) {
                case 1 :
                    // org/jf/smali/smaliTreeWalker.g:1331:4: VOID_TYPE
                    {
                    match(input,VOID_TYPE,FOLLOW_VOID_TYPE_in_type_descriptor2635); 
                    type = TypeIdItem.internTypeIdItem(dexFile, "V");

                    }
                    break;
                case 2 :
                    // org/jf/smali/smaliTreeWalker.g:1332:4: nonvoid_type_descriptor
                    {
                    pushFollow(FOLLOW_nonvoid_type_descriptor_in_type_descriptor2642);
                    nonvoid_type_descriptor203=nonvoid_type_descriptor();

                    state._fsp--;

                    type = (nonvoid_type_descriptor203!=null?nonvoid_type_descriptor203.type:null);

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return type;
    }
    // $ANTLR end "type_descriptor"


    // $ANTLR start "short_integral_literal"
    // org/jf/smali/smaliTreeWalker.g:1335:1: short_integral_literal returns [short value] : ( long_literal | integer_literal | short_literal | char_literal | byte_literal );
    public final short short_integral_literal() throws RecognitionException {
        short value = 0;

        long long_literal204 = 0;

        int integer_literal205 = 0;

        short short_literal206 = 0;

        char char_literal207 = 0;

        byte byte_literal208 = 0;


        try {
            // org/jf/smali/smaliTreeWalker.g:1336:2: ( long_literal | integer_literal | short_literal | char_literal | byte_literal )
            int alt36=5;
            switch ( input.LA(1) ) {
            case LONG_LITERAL:
                {
                alt36=1;
                }
                break;
            case INTEGER_LITERAL:
                {
                alt36=2;
                }
                break;
            case SHORT_LITERAL:
                {
                alt36=3;
                }
                break;
            case CHAR_LITERAL:
                {
                alt36=4;
                }
                break;
            case BYTE_LITERAL:
                {
                alt36=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 36, 0, input);

                throw nvae;
            }

            switch (alt36) {
                case 1 :
                    // org/jf/smali/smaliTreeWalker.g:1336:4: long_literal
                    {
                    pushFollow(FOLLOW_long_literal_in_short_integral_literal2658);
                    long_literal204=long_literal();

                    state._fsp--;


                    			literalTools.checkShort(long_literal204);
                    			value = (short)long_literal204;
                    		

                    }
                    break;
                case 2 :
                    // org/jf/smali/smaliTreeWalker.g:1341:4: integer_literal
                    {
                    pushFollow(FOLLOW_integer_literal_in_short_integral_literal2667);
                    integer_literal205=integer_literal();

                    state._fsp--;


                    			literalTools.checkShort(integer_literal205);
                    			value = (short)integer_literal205;
                    		

                    }
                    break;
                case 3 :
                    // org/jf/smali/smaliTreeWalker.g:1346:4: short_literal
                    {
                    pushFollow(FOLLOW_short_literal_in_short_integral_literal2676);
                    short_literal206=short_literal();

                    state._fsp--;

                    value = short_literal206;

                    }
                    break;
                case 4 :
                    // org/jf/smali/smaliTreeWalker.g:1347:4: char_literal
                    {
                    pushFollow(FOLLOW_char_literal_in_short_integral_literal2683);
                    char_literal207=char_literal();

                    state._fsp--;

                    value = (short)char_literal207;

                    }
                    break;
                case 5 :
                    // org/jf/smali/smaliTreeWalker.g:1348:4: byte_literal
                    {
                    pushFollow(FOLLOW_byte_literal_in_short_integral_literal2690);
                    byte_literal208=byte_literal();

                    state._fsp--;

                    value = byte_literal208;

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return value;
    }
    // $ANTLR end "short_integral_literal"


    // $ANTLR start "integral_literal"
    // org/jf/smali/smaliTreeWalker.g:1350:1: integral_literal returns [int value] : ( long_literal | integer_literal | short_literal | byte_literal );
    public final int integral_literal() throws RecognitionException {
        int value = 0;

        long long_literal209 = 0;

        int integer_literal210 = 0;

        short short_literal211 = 0;

        byte byte_literal212 = 0;


        try {
            // org/jf/smali/smaliTreeWalker.g:1351:2: ( long_literal | integer_literal | short_literal | byte_literal )
            int alt37=4;
            switch ( input.LA(1) ) {
            case LONG_LITERAL:
                {
                alt37=1;
                }
                break;
            case INTEGER_LITERAL:
                {
                alt37=2;
                }
                break;
            case SHORT_LITERAL:
                {
                alt37=3;
                }
                break;
            case BYTE_LITERAL:
                {
                alt37=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 37, 0, input);

                throw nvae;
            }

            switch (alt37) {
                case 1 :
                    // org/jf/smali/smaliTreeWalker.g:1351:4: long_literal
                    {
                    pushFollow(FOLLOW_long_literal_in_integral_literal2704);
                    long_literal209=long_literal();

                    state._fsp--;


                    			literalTools.checkInt(long_literal209);
                    			value = (int)long_literal209;
                    		

                    }
                    break;
                case 2 :
                    // org/jf/smali/smaliTreeWalker.g:1356:4: integer_literal
                    {
                    pushFollow(FOLLOW_integer_literal_in_integral_literal2713);
                    integer_literal210=integer_literal();

                    state._fsp--;

                    value = integer_literal210;

                    }
                    break;
                case 3 :
                    // org/jf/smali/smaliTreeWalker.g:1357:4: short_literal
                    {
                    pushFollow(FOLLOW_short_literal_in_integral_literal2720);
                    short_literal211=short_literal();

                    state._fsp--;

                    value = short_literal211;

                    }
                    break;
                case 4 :
                    // org/jf/smali/smaliTreeWalker.g:1358:4: byte_literal
                    {
                    pushFollow(FOLLOW_byte_literal_in_integral_literal2727);
                    byte_literal212=byte_literal();

                    state._fsp--;

                    value = byte_literal212;

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return value;
    }
    // $ANTLR end "integral_literal"


    // $ANTLR start "integer_literal"
    // org/jf/smali/smaliTreeWalker.g:1361:1: integer_literal returns [int value] : INTEGER_LITERAL ;
    public final int integer_literal() throws RecognitionException {
        int value = 0;

        CommonTree INTEGER_LITERAL213=null;

        try {
            // org/jf/smali/smaliTreeWalker.g:1362:2: ( INTEGER_LITERAL )
            // org/jf/smali/smaliTreeWalker.g:1362:4: INTEGER_LITERAL
            {
            INTEGER_LITERAL213=(CommonTree)match(input,INTEGER_LITERAL,FOLLOW_INTEGER_LITERAL_in_integer_literal2742); 
             value = literalTools.parseInt((INTEGER_LITERAL213!=null?INTEGER_LITERAL213.getText():null)); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return value;
    }
    // $ANTLR end "integer_literal"


    // $ANTLR start "long_literal"
    // org/jf/smali/smaliTreeWalker.g:1364:1: long_literal returns [long value] : LONG_LITERAL ;
    public final long long_literal() throws RecognitionException {
        long value = 0;

        CommonTree LONG_LITERAL214=null;

        try {
            // org/jf/smali/smaliTreeWalker.g:1365:2: ( LONG_LITERAL )
            // org/jf/smali/smaliTreeWalker.g:1365:4: LONG_LITERAL
            {
            LONG_LITERAL214=(CommonTree)match(input,LONG_LITERAL,FOLLOW_LONG_LITERAL_in_long_literal2756); 
             value = literalTools.parseLong((LONG_LITERAL214!=null?LONG_LITERAL214.getText():null)); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return value;
    }
    // $ANTLR end "long_literal"


    // $ANTLR start "short_literal"
    // org/jf/smali/smaliTreeWalker.g:1367:1: short_literal returns [short value] : SHORT_LITERAL ;
    public final short short_literal() throws RecognitionException {
        short value = 0;

        CommonTree SHORT_LITERAL215=null;

        try {
            // org/jf/smali/smaliTreeWalker.g:1368:2: ( SHORT_LITERAL )
            // org/jf/smali/smaliTreeWalker.g:1368:4: SHORT_LITERAL
            {
            SHORT_LITERAL215=(CommonTree)match(input,SHORT_LITERAL,FOLLOW_SHORT_LITERAL_in_short_literal2770); 
             value = literalTools.parseShort((SHORT_LITERAL215!=null?SHORT_LITERAL215.getText():null)); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return value;
    }
    // $ANTLR end "short_literal"


    // $ANTLR start "byte_literal"
    // org/jf/smali/smaliTreeWalker.g:1370:1: byte_literal returns [byte value] : BYTE_LITERAL ;
    public final byte byte_literal() throws RecognitionException {
        byte value = 0;

        CommonTree BYTE_LITERAL216=null;

        try {
            // org/jf/smali/smaliTreeWalker.g:1371:2: ( BYTE_LITERAL )
            // org/jf/smali/smaliTreeWalker.g:1371:4: BYTE_LITERAL
            {
            BYTE_LITERAL216=(CommonTree)match(input,BYTE_LITERAL,FOLLOW_BYTE_LITERAL_in_byte_literal2784); 
             value = literalTools.parseByte((BYTE_LITERAL216!=null?BYTE_LITERAL216.getText():null)); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return value;
    }
    // $ANTLR end "byte_literal"


    // $ANTLR start "float_literal"
    // org/jf/smali/smaliTreeWalker.g:1373:1: float_literal returns [float value] : FLOAT_LITERAL ;
    public final float float_literal() throws RecognitionException {
        float value = 0.0f;

        CommonTree FLOAT_LITERAL217=null;

        try {
            // org/jf/smali/smaliTreeWalker.g:1374:2: ( FLOAT_LITERAL )
            // org/jf/smali/smaliTreeWalker.g:1374:4: FLOAT_LITERAL
            {
            FLOAT_LITERAL217=(CommonTree)match(input,FLOAT_LITERAL,FOLLOW_FLOAT_LITERAL_in_float_literal2798); 
             value = parseFloat((FLOAT_LITERAL217!=null?FLOAT_LITERAL217.getText():null)); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return value;
    }
    // $ANTLR end "float_literal"


    // $ANTLR start "double_literal"
    // org/jf/smali/smaliTreeWalker.g:1376:1: double_literal returns [double value] : DOUBLE_LITERAL ;
    public final double double_literal() throws RecognitionException {
        double value = 0.0;

        CommonTree DOUBLE_LITERAL218=null;

        try {
            // org/jf/smali/smaliTreeWalker.g:1377:2: ( DOUBLE_LITERAL )
            // org/jf/smali/smaliTreeWalker.g:1377:4: DOUBLE_LITERAL
            {
            DOUBLE_LITERAL218=(CommonTree)match(input,DOUBLE_LITERAL,FOLLOW_DOUBLE_LITERAL_in_double_literal2812); 
             value = parseDouble((DOUBLE_LITERAL218!=null?DOUBLE_LITERAL218.getText():null)); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return value;
    }
    // $ANTLR end "double_literal"


    // $ANTLR start "char_literal"
    // org/jf/smali/smaliTreeWalker.g:1379:1: char_literal returns [char value] : CHAR_LITERAL ;
    public final char char_literal() throws RecognitionException {
        char value = 0;

        CommonTree CHAR_LITERAL219=null;

        try {
            // org/jf/smali/smaliTreeWalker.g:1380:2: ( CHAR_LITERAL )
            // org/jf/smali/smaliTreeWalker.g:1380:4: CHAR_LITERAL
            {
            CHAR_LITERAL219=(CommonTree)match(input,CHAR_LITERAL,FOLLOW_CHAR_LITERAL_in_char_literal2826); 
             value = (CHAR_LITERAL219!=null?CHAR_LITERAL219.getText():null).charAt(1); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return value;
    }
    // $ANTLR end "char_literal"


    // $ANTLR start "string_literal"
    // org/jf/smali/smaliTreeWalker.g:1382:1: string_literal returns [String value] : STRING_LITERAL ;
    public final String string_literal() throws RecognitionException {
        String value = null;

        CommonTree STRING_LITERAL220=null;

        try {
            // org/jf/smali/smaliTreeWalker.g:1383:2: ( STRING_LITERAL )
            // org/jf/smali/smaliTreeWalker.g:1383:4: STRING_LITERAL
            {
            STRING_LITERAL220=(CommonTree)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_string_literal2840); 

            			value = (STRING_LITERAL220!=null?STRING_LITERAL220.getText():null);
            			value = value.substring(1,value.length()-1);
            		

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return value;
    }
    // $ANTLR end "string_literal"


    // $ANTLR start "bool_literal"
    // org/jf/smali/smaliTreeWalker.g:1389:1: bool_literal returns [boolean value] : BOOL_LITERAL ;
    public final boolean bool_literal() throws RecognitionException {
        boolean value = false;

        CommonTree BOOL_LITERAL221=null;

        try {
            // org/jf/smali/smaliTreeWalker.g:1390:2: ( BOOL_LITERAL )
            // org/jf/smali/smaliTreeWalker.g:1390:4: BOOL_LITERAL
            {
            BOOL_LITERAL221=(CommonTree)match(input,BOOL_LITERAL,FOLLOW_BOOL_LITERAL_in_bool_literal2856); 
             value = Boolean.parseBoolean((BOOL_LITERAL221!=null?BOOL_LITERAL221.getText():null)); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return value;
    }
    // $ANTLR end "bool_literal"


    // $ANTLR start "array_literal"
    // org/jf/smali/smaliTreeWalker.g:1392:1: array_literal returns [EncodedValue[] values] : ^( I_ENCODED_ARRAY ( literal )* ) ;
    public final EncodedValue[] array_literal() throws RecognitionException {
        EncodedValue[] values = null;

        EncodedValue literal222 = null;


        try {
            // org/jf/smali/smaliTreeWalker.g:1393:2: ( ^( I_ENCODED_ARRAY ( literal )* ) )
            // org/jf/smali/smaliTreeWalker.g:1393:4: ^( I_ENCODED_ARRAY ( literal )* )
            {
            ArrayList<EncodedValue> valuesList = new ArrayList<EncodedValue>();
            match(input,I_ENCODED_ARRAY,FOLLOW_I_ENCODED_ARRAY_in_array_literal2875); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // org/jf/smali/smaliTreeWalker.g:1394:21: ( literal )*
                loop38:
                do {
                    int alt38=2;
                    switch ( input.LA(1) ) {
                    case LONG_LITERAL:
                    case SHORT_LITERAL:
                    case BYTE_LITERAL:
                    case FLOAT_LITERAL:
                    case DOUBLE_LITERAL:
                    case BOOL_LITERAL:
                    case NULL_LITERAL:
                    case STRING_LITERAL:
                    case CHAR_LITERAL:
                    case PRIMITIVE_TYPE:
                    case VOID_TYPE:
                    case CLASS_DESCRIPTOR:
                    case ARRAY_DESCRIPTOR:
                    case I_SUBANNOTATION:
                    case I_ENCODED_FIELD:
                    case I_ENCODED_METHOD:
                    case I_ENCODED_ENUM:
                    case I_ENCODED_ARRAY:
                    case INTEGER_LITERAL:
                        {
                        alt38=1;
                        }
                        break;

                    }

                    switch (alt38) {
                	case 1 :
                	    // org/jf/smali/smaliTreeWalker.g:1394:22: literal
                	    {
                	    pushFollow(FOLLOW_literal_in_array_literal2878);
                	    literal222=literal();

                	    state._fsp--;

                	    valuesList.add(literal222);

                	    }
                	    break;

                	default :
                	    break loop38;
                    }
                } while (true);


                match(input, Token.UP, null); 
            }

            			values = new EncodedValue[valuesList.size()];
            			valuesList.toArray(values);
            		

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return values;
    }
    // $ANTLR end "array_literal"


    // $ANTLR start "annotations"
    // org/jf/smali/smaliTreeWalker.g:1401:1: annotations returns [AnnotationSetItem annotationSetItem] : ^( I_ANNOTATIONS ( annotation )* ) ;
    public final AnnotationSetItem annotations() throws RecognitionException {
        AnnotationSetItem annotationSetItem = null;

        AnnotationItem annotation223 = null;


        try {
            // org/jf/smali/smaliTreeWalker.g:1402:2: ( ^( I_ANNOTATIONS ( annotation )* ) )
            // org/jf/smali/smaliTreeWalker.g:1402:4: ^( I_ANNOTATIONS ( annotation )* )
            {
            ArrayList<AnnotationItem> annotationList = new ArrayList<AnnotationItem>();
            match(input,I_ANNOTATIONS,FOLLOW_I_ANNOTATIONS_in_annotations2905); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // org/jf/smali/smaliTreeWalker.g:1403:19: ( annotation )*
                loop39:
                do {
                    int alt39=2;
                    switch ( input.LA(1) ) {
                    case I_ANNOTATION:
                        {
                        alt39=1;
                        }
                        break;

                    }

                    switch (alt39) {
                	case 1 :
                	    // org/jf/smali/smaliTreeWalker.g:1403:20: annotation
                	    {
                	    pushFollow(FOLLOW_annotation_in_annotations2908);
                	    annotation223=annotation();

                	    state._fsp--;

                	    annotationList.add(annotation223);

                	    }
                	    break;

                	default :
                	    break loop39;
                    }
                } while (true);


                match(input, Token.UP, null); 
            }

            			if (annotationList.size() > 0) {
            				annotationSetItem = AnnotationSetItem.internAnnotationSetItem(dexFile, annotationList);
            			}
            		

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return annotationSetItem;
    }
    // $ANTLR end "annotations"


    // $ANTLR start "annotation"
    // org/jf/smali/smaliTreeWalker.g:1411:1: annotation returns [AnnotationItem annotationItem] : ^( I_ANNOTATION ANNOTATION_VISIBILITY subannotation ) ;
    public final AnnotationItem annotation() throws RecognitionException {
        AnnotationItem annotationItem = null;

        CommonTree ANNOTATION_VISIBILITY224=null;
        smaliTreeWalker.subannotation_return subannotation225 = null;


        try {
            // org/jf/smali/smaliTreeWalker.g:1412:2: ( ^( I_ANNOTATION ANNOTATION_VISIBILITY subannotation ) )
            // org/jf/smali/smaliTreeWalker.g:1412:4: ^( I_ANNOTATION ANNOTATION_VISIBILITY subannotation )
            {
            match(input,I_ANNOTATION,FOLLOW_I_ANNOTATION_in_annotation2932); 

            match(input, Token.DOWN, null); 
            ANNOTATION_VISIBILITY224=(CommonTree)match(input,ANNOTATION_VISIBILITY,FOLLOW_ANNOTATION_VISIBILITY_in_annotation2934); 
            pushFollow(FOLLOW_subannotation_in_annotation2936);
            subannotation225=subannotation();

            state._fsp--;


            match(input, Token.UP, null); 

            			AnnotationVisibility visibility = AnnotationVisibility.valueOf((ANNOTATION_VISIBILITY224!=null?ANNOTATION_VISIBILITY224.getText():null).toUpperCase());
            			AnnotationEncodedSubValue encodedAnnotation = new AnnotationEncodedSubValue((subannotation225!=null?subannotation225.annotationType:null),
            					(subannotation225!=null?subannotation225.elementNames:null), (subannotation225!=null?subannotation225.elementValues:null));
            			annotationItem = AnnotationItem.internAnnotationItem(dexFile, visibility, encodedAnnotation);
            		

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return annotationItem;
    }
    // $ANTLR end "annotation"

    public static class annotation_element_return extends TreeRuleReturnScope {
        public StringIdItem elementName;
        public EncodedValue elementValue;
    };

    // $ANTLR start "annotation_element"
    // org/jf/smali/smaliTreeWalker.g:1420:1: annotation_element returns [StringIdItem elementName, EncodedValue elementValue] : ^( I_ANNOTATION_ELEMENT SIMPLE_NAME literal ) ;
    public final smaliTreeWalker.annotation_element_return annotation_element() throws RecognitionException {
        smaliTreeWalker.annotation_element_return retval = new smaliTreeWalker.annotation_element_return();
        retval.start = input.LT(1);

        CommonTree SIMPLE_NAME226=null;
        EncodedValue literal227 = null;


        try {
            // org/jf/smali/smaliTreeWalker.g:1421:2: ( ^( I_ANNOTATION_ELEMENT SIMPLE_NAME literal ) )
            // org/jf/smali/smaliTreeWalker.g:1421:4: ^( I_ANNOTATION_ELEMENT SIMPLE_NAME literal )
            {
            match(input,I_ANNOTATION_ELEMENT,FOLLOW_I_ANNOTATION_ELEMENT_in_annotation_element2954); 

            match(input, Token.DOWN, null); 
            SIMPLE_NAME226=(CommonTree)match(input,SIMPLE_NAME,FOLLOW_SIMPLE_NAME_in_annotation_element2956); 
            pushFollow(FOLLOW_literal_in_annotation_element2958);
            literal227=literal();

            state._fsp--;


            match(input, Token.UP, null); 

            			retval.elementName = StringIdItem.internStringIdItem(dexFile, (SIMPLE_NAME226!=null?SIMPLE_NAME226.getText():null));
            			retval.elementValue = literal227;
            		

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "annotation_element"

    public static class subannotation_return extends TreeRuleReturnScope {
        public TypeIdItem annotationType;
        public StringIdItem[] elementNames;
        public EncodedValue[] elementValues;
    };

    // $ANTLR start "subannotation"
    // org/jf/smali/smaliTreeWalker.g:1427:1: subannotation returns [TypeIdItem annotationType, StringIdItem[] elementNames, EncodedValue[] elementValues] : ^( I_SUBANNOTATION class_type_descriptor ( annotation_element )* ) ;
    public final smaliTreeWalker.subannotation_return subannotation() throws RecognitionException {
        smaliTreeWalker.subannotation_return retval = new smaliTreeWalker.subannotation_return();
        retval.start = input.LT(1);

        smaliTreeWalker.annotation_element_return annotation_element228 = null;

        TypeIdItem class_type_descriptor229 = null;


        try {
            // org/jf/smali/smaliTreeWalker.g:1428:2: ( ^( I_SUBANNOTATION class_type_descriptor ( annotation_element )* ) )
            // org/jf/smali/smaliTreeWalker.g:1428:4: ^( I_SUBANNOTATION class_type_descriptor ( annotation_element )* )
            {
            ArrayList<StringIdItem> elementNamesList = new ArrayList<StringIdItem>();
            		ArrayList<EncodedValue> elementValuesList = new ArrayList<EncodedValue>();
            match(input,I_SUBANNOTATION,FOLLOW_I_SUBANNOTATION_in_subannotation2981); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_class_type_descriptor_in_subannotation2986);
            class_type_descriptor229=class_type_descriptor();

            state._fsp--;

            // org/jf/smali/smaliTreeWalker.g:1432:4: ( annotation_element )*
            loop40:
            do {
                int alt40=2;
                switch ( input.LA(1) ) {
                case I_ANNOTATION_ELEMENT:
                    {
                    alt40=1;
                    }
                    break;

                }

                switch (alt40) {
            	case 1 :
            	    // org/jf/smali/smaliTreeWalker.g:1432:5: annotation_element
            	    {
            	    pushFollow(FOLLOW_annotation_element_in_subannotation2992);
            	    annotation_element228=annotation_element();

            	    state._fsp--;


            	    				elementNamesList.add((annotation_element228!=null?annotation_element228.elementName:null));
            	    				elementValuesList.add((annotation_element228!=null?annotation_element228.elementValue:null));
            	    			

            	    }
            	    break;

            	default :
            	    break loop40;
                }
            } while (true);


            match(input, Token.UP, null); 

            			retval.annotationType = class_type_descriptor229;
            			retval.elementNames = new StringIdItem[elementNamesList.size()];
            			elementNamesList.toArray(retval.elementNames);
            			retval.elementValues = new EncodedValue[elementValuesList.size()];
            			elementValuesList.toArray(retval.elementValues);
            		

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "subannotation"


    // $ANTLR start "field_literal"
    // org/jf/smali/smaliTreeWalker.g:1445:1: field_literal returns [FieldIdItem value] : ^( I_ENCODED_FIELD fully_qualified_field ) ;
    public final FieldIdItem field_literal() throws RecognitionException {
        FieldIdItem value = null;

        FieldIdItem fully_qualified_field230 = null;


        try {
            // org/jf/smali/smaliTreeWalker.g:1446:2: ( ^( I_ENCODED_FIELD fully_qualified_field ) )
            // org/jf/smali/smaliTreeWalker.g:1446:4: ^( I_ENCODED_FIELD fully_qualified_field )
            {
            match(input,I_ENCODED_FIELD,FOLLOW_I_ENCODED_FIELD_in_field_literal3019); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_fully_qualified_field_in_field_literal3021);
            fully_qualified_field230=fully_qualified_field();

            state._fsp--;


            match(input, Token.UP, null); 

            			value = fully_qualified_field230;
            		

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return value;
    }
    // $ANTLR end "field_literal"


    // $ANTLR start "method_literal"
    // org/jf/smali/smaliTreeWalker.g:1451:1: method_literal returns [MethodIdItem value] : ^( I_ENCODED_METHOD fully_qualified_method ) ;
    public final MethodIdItem method_literal() throws RecognitionException {
        MethodIdItem value = null;

        MethodIdItem fully_qualified_method231 = null;


        try {
            // org/jf/smali/smaliTreeWalker.g:1452:2: ( ^( I_ENCODED_METHOD fully_qualified_method ) )
            // org/jf/smali/smaliTreeWalker.g:1452:4: ^( I_ENCODED_METHOD fully_qualified_method )
            {
            match(input,I_ENCODED_METHOD,FOLLOW_I_ENCODED_METHOD_in_method_literal3039); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_fully_qualified_method_in_method_literal3041);
            fully_qualified_method231=fully_qualified_method();

            state._fsp--;


            match(input, Token.UP, null); 

            			value = fully_qualified_method231;
            		

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return value;
    }
    // $ANTLR end "method_literal"


    // $ANTLR start "enum_literal"
    // org/jf/smali/smaliTreeWalker.g:1457:1: enum_literal returns [FieldIdItem value] : ^( I_ENCODED_ENUM fully_qualified_field ) ;
    public final FieldIdItem enum_literal() throws RecognitionException {
        FieldIdItem value = null;

        FieldIdItem fully_qualified_field232 = null;


        try {
            // org/jf/smali/smaliTreeWalker.g:1458:2: ( ^( I_ENCODED_ENUM fully_qualified_field ) )
            // org/jf/smali/smaliTreeWalker.g:1458:4: ^( I_ENCODED_ENUM fully_qualified_field )
            {
            match(input,I_ENCODED_ENUM,FOLLOW_I_ENCODED_ENUM_in_enum_literal3059); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_fully_qualified_field_in_enum_literal3061);
            fully_qualified_field232=fully_qualified_field();

            state._fsp--;


            match(input, Token.UP, null); 

            			value = fully_qualified_field232;
            		

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return value;
    }
    // $ANTLR end "enum_literal"

    // Delegated rules


 

    public static final BitSet FOLLOW_I_CLASS_DEF_in_smali_file48 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_header_in_smali_file50 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_methods_in_smali_file52 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_fields_in_smali_file54 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_annotations_in_smali_file56 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_class_spec_in_header80 = new BitSet(new long[]{0x0000000000000000L,0xC000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_super_spec_in_header82 = new BitSet(new long[]{0x0000000000000000L,0x8000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_implements_list_in_header85 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_source_spec_in_header87 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_class_type_descriptor_in_class_spec103 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_access_list_in_class_spec105 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_I_SUPER_in_super_spec121 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_class_type_descriptor_in_super_spec123 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_I_IMPLEMENTS_in_implements_spec141 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_class_type_descriptor_in_implements_spec143 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_implements_spec_in_implements_list169 = new BitSet(new long[]{0x0000000000000002L,0x8000000000000000L});
    public static final BitSet FOLLOW_I_SOURCE_in_source_spec194 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_string_literal_in_source_spec196 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_I_ACCESS_LIST_in_access_list226 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ACCESS_SPEC_in_access_list237 = new BitSet(new long[]{0x2000000000000008L});
    public static final BitSet FOLLOW_I_FIELDS_in_fields271 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_field_in_fields277 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_I_METHODS_in_methods305 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_method_in_methods311 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_I_FIELD_in_field332 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_SIMPLE_NAME_in_field334 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_access_list_in_field336 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_I_FIELD_TYPE_in_field339 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_nonvoid_type_descriptor_in_field341 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_field_initial_value_in_field344 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_annotations_in_field346 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_I_FIELD_INITIAL_VALUE_in_field_initial_value365 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_literal_in_field_initial_value367 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_integer_literal_in_literal386 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_long_literal_in_literal393 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_short_literal_in_literal400 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_byte_literal_in_literal407 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_float_literal_in_literal414 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_double_literal_in_literal421 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_char_literal_in_literal428 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_string_literal_in_literal435 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_bool_literal_in_literal442 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NULL_LITERAL_in_literal449 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_descriptor_in_literal456 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_array_literal_in_literal463 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_subannotation_in_literal470 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_field_literal_in_literal477 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_method_literal_in_literal484 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_enum_literal_in_literal491 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_integer_literal_in_fixed_size_literal507 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_long_literal_in_fixed_size_literal514 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_short_literal_in_fixed_size_literal521 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_byte_literal_in_fixed_size_literal528 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_float_literal_in_fixed_size_literal535 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_double_literal_in_fixed_size_literal542 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_char_literal_in_fixed_size_literal549 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_bool_literal_in_fixed_size_literal556 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_integer_literal_in_fixed_64bit_literal571 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_long_literal_in_fixed_64bit_literal578 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_short_literal_in_fixed_64bit_literal585 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_byte_literal_in_fixed_64bit_literal592 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_float_literal_in_fixed_64bit_literal599 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_double_literal_in_fixed_64bit_literal606 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_char_literal_in_fixed_64bit_literal613 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_bool_literal_in_fixed_64bit_literal620 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_integer_literal_in_fixed_32bit_literal636 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_long_literal_in_fixed_32bit_literal643 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_short_literal_in_fixed_32bit_literal650 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_byte_literal_in_fixed_32bit_literal657 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_float_literal_in_fixed_32bit_literal664 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_char_literal_in_fixed_32bit_literal671 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_bool_literal_in_fixed_32bit_literal678 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_I_ARRAY_ELEMENTS_in_array_elements697 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_fixed_size_literal_in_array_elements703 = new BitSet(new long[]{0x0439C00000000008L,0x0000000000000000L,0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_I_PACKED_SWITCH_TARGET_COUNT_in_packed_switch_target_count723 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_I_PACKED_SWITCH_TARGETS_in_packed_switch_targets741 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_packed_switch_target_count_in_packed_switch_targets746 = new BitSet(new long[]{0x0000000000000008L,0x0002000000000001L});
    public static final BitSet FOLLOW_offset_or_label_in_packed_switch_targets758 = new BitSet(new long[]{0x0000000000000008L,0x0002000000000001L});
    public static final BitSet FOLLOW_I_SPARSE_SWITCH_TARGET_COUNT_in_sparse_switch_target_count781 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_I_SPARSE_SWITCH_KEYS_in_sparse_switch_keys801 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_fixed_32bit_literal_in_sparse_switch_keys807 = new BitSet(new long[]{0x0439C00000000008L,0x0000000000000000L,0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_I_SPARSE_SWITCH_TARGETS_in_sparse_switch_targets837 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_offset_or_label_in_sparse_switch_targets843 = new BitSet(new long[]{0x0000000000000008L,0x0002000000000001L});
    public static final BitSet FOLLOW_I_METHOD_in_method885 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_method_name_and_prototype_in_method890 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_access_list_in_method895 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000001C00L});
    public static final BitSet FOLLOW_registers_directive_in_method907 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000001C00L});
    public static final BitSet FOLLOW_labels_in_method924 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000010000000L});
    public static final BitSet FOLLOW_packed_switch_declarations_in_method929 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000200000000L});
    public static final BitSet FOLLOW_sparse_switch_declarations_in_method934 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0001000000000000L});
    public static final BitSet FOLLOW_statements_in_method939 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000002000000000L});
    public static final BitSet FOLLOW_catches_in_method945 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000008000000000L});
    public static final BitSet FOLLOW_parameters_in_method950 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000020000000000L});
    public static final BitSet FOLLOW_ordered_debug_directives_in_method955 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_annotations_in_method961 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_I_METHOD_PROTOTYPE_in_method_prototype981 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_I_METHOD_RETURN_TYPE_in_method_prototype984 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_type_descriptor_in_method_prototype986 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_field_type_list_in_method_prototype989 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_SIMPLE_NAME_in_method_name_and_prototype1005 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_method_prototype_in_method_name_and_prototype1007 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_nonvoid_type_descriptor_in_field_type_list1034 = new BitSet(new long[]{0x0000000000000002L,0x0000680000000000L});
    public static final BitSet FOLLOW_reference_type_descriptor_in_fully_qualified_method1057 = new BitSet(new long[]{0x0000000000000000L,0x0002000000000000L});
    public static final BitSet FOLLOW_SIMPLE_NAME_in_fully_qualified_method1059 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_method_prototype_in_fully_qualified_method1061 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_reference_type_descriptor_in_fully_qualified_field1076 = new BitSet(new long[]{0x0000000000000000L,0x0002000000000000L});
    public static final BitSet FOLLOW_SIMPLE_NAME_in_fully_qualified_field1078 = new BitSet(new long[]{0x0000000000000000L,0x0000680000000000L});
    public static final BitSet FOLLOW_nonvoid_type_descriptor_in_fully_qualified_field1080 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_I_REGISTERS_in_registers_directive1103 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_I_LOCALS_in_registers_directive1112 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_short_integral_literal_in_registers_directive1124 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_I_LABELS_in_labels1140 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_label_def_in_labels1142 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_I_LABEL_in_label_def1154 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_SIMPLE_NAME_in_label_def1156 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000400000000L});
    public static final BitSet FOLLOW_address_in_label_def1158 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_I_PACKED_SWITCH_DECLARATIONS_in_packed_switch_declarations1173 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_packed_switch_declaration_in_packed_switch_declarations1175 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0000000008000000L});
    public static final BitSet FOLLOW_I_PACKED_SWITCH_DECLARATION_in_packed_switch_declaration1186 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_address_in_packed_switch_declaration1188 = new BitSet(new long[]{0x0000000000000000L,0x0002000000000001L});
    public static final BitSet FOLLOW_offset_or_label_absolute_in_packed_switch_declaration1190 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_I_SPARSE_SWITCH_DECLARATIONS_in_sparse_switch_declarations1206 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_sparse_switch_declaration_in_sparse_switch_declarations1208 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0000000100000000L});
    public static final BitSet FOLLOW_I_SPARSE_SWITCH_DECLARATION_in_sparse_switch_declaration1219 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_address_in_sparse_switch_declaration1221 = new BitSet(new long[]{0x0000000000000000L,0x0002000000000001L});
    public static final BitSet FOLLOW_offset_or_label_absolute_in_sparse_switch_declaration1223 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_I_CATCHES_in_catches1238 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_catch_directive_in_catches1240 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0000001800000000L});
    public static final BitSet FOLLOW_catchall_directive_in_catches1243 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0000001000000000L});
    public static final BitSet FOLLOW_I_CATCH_in_catch_directive1255 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_address_in_catch_directive1257 = new BitSet(new long[]{0x0000000000000000L,0x0002680000000001L});
    public static final BitSet FOLLOW_nonvoid_type_descriptor_in_catch_directive1259 = new BitSet(new long[]{0x0000000000000000L,0x0002000000000001L});
    public static final BitSet FOLLOW_offset_or_label_absolute_in_catch_directive1263 = new BitSet(new long[]{0x0000000000000000L,0x0002000000000001L});
    public static final BitSet FOLLOW_offset_or_label_absolute_in_catch_directive1268 = new BitSet(new long[]{0x0000000000000000L,0x0002000000000001L});
    public static final BitSet FOLLOW_offset_or_label_absolute_in_catch_directive1277 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_I_CATCHALL_in_catchall_directive1293 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_address_in_catchall_directive1295 = new BitSet(new long[]{0x0000000000000000L,0x0002000000000001L});
    public static final BitSet FOLLOW_offset_or_label_absolute_in_catchall_directive1299 = new BitSet(new long[]{0x0000000000000000L,0x0002000000000001L});
    public static final BitSet FOLLOW_offset_or_label_absolute_in_catchall_directive1304 = new BitSet(new long[]{0x0000000000000000L,0x0002000000000001L});
    public static final BitSet FOLLOW_offset_or_label_absolute_in_catchall_directive1313 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_I_ADDRESS_in_address1331 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_I_PARAMETERS_in_parameters1355 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_parameter_in_parameters1358 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0000004000000000L});
    public static final BitSet FOLLOW_I_PARAMETER_in_parameter1387 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_string_literal_in_parameter1392 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_annotations_in_parameter1414 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_I_ORDERED_DEBUG_DIRECTIVES_in_ordered_debug_directives1431 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_line_in_ordered_debug_directives1436 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0000FC0000000001L});
    public static final BitSet FOLLOW_local_in_ordered_debug_directives1447 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0000FC0000000001L});
    public static final BitSet FOLLOW_end_local_in_ordered_debug_directives1458 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0000FC0000000001L});
    public static final BitSet FOLLOW_restart_local_in_ordered_debug_directives1469 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0000FC0000000001L});
    public static final BitSet FOLLOW_prologue_in_ordered_debug_directives1480 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0000FC0000000001L});
    public static final BitSet FOLLOW_epilogue_in_ordered_debug_directives1490 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0000FC0000000001L});
    public static final BitSet FOLLOW_source_in_ordered_debug_directives1500 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0000FC0000000001L});
    public static final BitSet FOLLOW_I_LINE_in_line1520 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_integral_literal_in_line1522 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000400000000L});
    public static final BitSet FOLLOW_address_in_line1524 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_I_LOCAL_in_local1540 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_REGISTER_in_local1542 = new BitSet(new long[]{0x0000000000000000L,0x0002000000000000L});
    public static final BitSet FOLLOW_SIMPLE_NAME_in_local1544 = new BitSet(new long[]{0x0100000000000000L,0x0000680000000000L,0x0000000400000000L});
    public static final BitSet FOLLOW_nonvoid_type_descriptor_in_local1546 = new BitSet(new long[]{0x0100000000000000L,0x0000000000000000L,0x0000000400000000L});
    public static final BitSet FOLLOW_string_literal_in_local1548 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000400000000L});
    public static final BitSet FOLLOW_address_in_local1551 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_I_END_LOCAL_in_end_local1567 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_REGISTER_in_end_local1569 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000400000000L});
    public static final BitSet FOLLOW_address_in_end_local1571 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_I_RESTART_LOCAL_in_restart_local1587 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_REGISTER_in_restart_local1589 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000400000000L});
    public static final BitSet FOLLOW_address_in_restart_local1591 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_I_PROLOGUE_in_prologue1606 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_address_in_prologue1608 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_I_EPILOGUE_in_epilogue1623 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_address_in_epilogue1625 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_I_SOURCE_in_source1640 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_string_literal_in_source1642 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000400000000L});
    public static final BitSet FOLLOW_address_in_source1644 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_I_STATEMENTS_in_statements1670 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_instruction_in_statements1673 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0xFFFE000000000000L,0x000000000001FFFFL});
    public static final BitSet FOLLOW_SIMPLE_NAME_in_label_ref1695 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OFFSET_in_offset1711 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_offset_in_offset_or_label_absolute1728 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_label_ref_in_offset_or_label_absolute1735 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_offset_in_offset_or_label1749 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_label_ref_in_offset_or_label1756 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_I_REGISTER_LIST_in_register_list1780 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_REGISTER_in_register_list1786 = new BitSet(new long[]{0x0800000000000008L});
    public static final BitSet FOLLOW_I_REGISTER_RANGE_in_register_range1808 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_REGISTER_in_register_range1812 = new BitSet(new long[]{0x0800000000000008L});
    public static final BitSet FOLLOW_REGISTER_in_register_range1816 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_I_STATEMENT_FORMAT10t_in_instruction1845 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_INSTRUCTION_FORMAT10t_in_instruction1847 = new BitSet(new long[]{0x0000000000000000L,0x0002000000000001L});
    public static final BitSet FOLLOW_offset_or_label_in_instruction1849 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_I_STATEMENT_FORMAT10x_in_instruction1863 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_INSTRUCTION_FORMAT10x_in_instruction1865 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_I_STATEMENT_FORMAT11n_in_instruction1879 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_INSTRUCTION_FORMAT11n_in_instruction1881 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_REGISTER_in_instruction1883 = new BitSet(new long[]{0x0401C00000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_short_integral_literal_in_instruction1885 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_I_STATEMENT_FORMAT11x_in_instruction1899 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_INSTRUCTION_FORMAT11x_in_instruction1901 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_REGISTER_in_instruction1903 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_I_STATEMENT_FORMAT12x_in_instruction1917 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_INSTRUCTION_FORMAT12x_in_instruction1919 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_REGISTER_in_instruction1923 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_REGISTER_in_instruction1927 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_I_STATEMENT_FORMAT20t_in_instruction1941 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_INSTRUCTION_FORMAT20t_in_instruction1943 = new BitSet(new long[]{0x0000000000000000L,0x0002000000000001L});
    public static final BitSet FOLLOW_offset_or_label_in_instruction1945 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_I_STATEMENT_FORMAT21c_FIELD_in_instruction1959 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_INSTRUCTION_FORMAT21c_FIELD_in_instruction1961 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_REGISTER_in_instruction1963 = new BitSet(new long[]{0x0000000000000000L,0x0000600000000000L});
    public static final BitSet FOLLOW_fully_qualified_field_in_instruction1965 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_I_STATEMENT_FORMAT21c_STRING_in_instruction1979 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_INSTRUCTION_FORMAT21c_STRING_in_instruction1981 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_REGISTER_in_instruction1983 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_string_literal_in_instruction1985 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_I_STATEMENT_FORMAT21c_TYPE_in_instruction1999 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_INSTRUCTION_FORMAT21c_TYPE_in_instruction2001 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_REGISTER_in_instruction2003 = new BitSet(new long[]{0x0000000000000000L,0x0000600000000000L});
    public static final BitSet FOLLOW_reference_type_descriptor_in_instruction2005 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_I_STATEMENT_FORMAT21h_in_instruction2019 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_INSTRUCTION_FORMAT21h_in_instruction2021 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_REGISTER_in_instruction2023 = new BitSet(new long[]{0x0401C00000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_short_integral_literal_in_instruction2025 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_I_STATEMENT_FORMAT21s_in_instruction2039 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_INSTRUCTION_FORMAT21s_in_instruction2041 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_REGISTER_in_instruction2043 = new BitSet(new long[]{0x0401C00000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_short_integral_literal_in_instruction2045 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_I_STATEMENT_FORMAT21t_in_instruction2059 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_INSTRUCTION_FORMAT21t_in_instruction2061 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_REGISTER_in_instruction2063 = new BitSet(new long[]{0x0000000000000000L,0x0002000000000001L});
    public static final BitSet FOLLOW_offset_or_label_in_instruction2065 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_I_STATEMENT_FORMAT22b_in_instruction2079 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_INSTRUCTION_FORMAT22b_in_instruction2081 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_REGISTER_in_instruction2085 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_REGISTER_in_instruction2089 = new BitSet(new long[]{0x0401C00000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_short_integral_literal_in_instruction2091 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_I_STATEMENT_FORMAT22c_FIELD_in_instruction2105 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_INSTRUCTION_FORMAT22c_FIELD_in_instruction2107 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_REGISTER_in_instruction2111 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_REGISTER_in_instruction2115 = new BitSet(new long[]{0x0000000000000000L,0x0000600000000000L});
    public static final BitSet FOLLOW_fully_qualified_field_in_instruction2117 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_I_STATEMENT_FORMAT22c_TYPE_in_instruction2131 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_INSTRUCTION_FORMAT22c_TYPE_in_instruction2133 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_REGISTER_in_instruction2137 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_REGISTER_in_instruction2141 = new BitSet(new long[]{0x0000000000000008L,0x0000680000000000L});
    public static final BitSet FOLLOW_nonvoid_type_descriptor_in_instruction2143 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_I_STATEMENT_FORMAT22s_in_instruction2157 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_INSTRUCTION_FORMAT22s_in_instruction2159 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_REGISTER_in_instruction2163 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_REGISTER_in_instruction2167 = new BitSet(new long[]{0x0401C00000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_short_integral_literal_in_instruction2169 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_I_STATEMENT_FORMAT22t_in_instruction2183 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_INSTRUCTION_FORMAT22t_in_instruction2185 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_REGISTER_in_instruction2189 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_REGISTER_in_instruction2193 = new BitSet(new long[]{0x0000000000000000L,0x0002000000000001L});
    public static final BitSet FOLLOW_offset_or_label_in_instruction2195 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_I_STATEMENT_FORMAT22x_in_instruction2209 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_INSTRUCTION_FORMAT22x_in_instruction2211 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_REGISTER_in_instruction2215 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_REGISTER_in_instruction2219 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_I_STATEMENT_FORMAT23x_in_instruction2233 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_INSTRUCTION_FORMAT23x_in_instruction2235 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_REGISTER_in_instruction2239 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_REGISTER_in_instruction2243 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_REGISTER_in_instruction2247 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_I_STATEMENT_FORMAT30t_in_instruction2261 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_INSTRUCTION_FORMAT30t_in_instruction2263 = new BitSet(new long[]{0x0000000000000000L,0x0002000000000001L});
    public static final BitSet FOLLOW_offset_or_label_in_instruction2265 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_I_STATEMENT_FORMAT31c_in_instruction2279 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_INSTRUCTION_FORMAT31c_in_instruction2281 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_REGISTER_in_instruction2283 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_string_literal_in_instruction2285 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_I_STATEMENT_FORMAT31i_in_instruction2299 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_INSTRUCTION_FORMAT31i_in_instruction2301 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_REGISTER_in_instruction2303 = new BitSet(new long[]{0x0439C00000000008L,0x0000000000000000L,0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_fixed_32bit_literal_in_instruction2305 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_I_STATEMENT_FORMAT31t_in_instruction2319 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_INSTRUCTION_FORMAT31t_in_instruction2321 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_REGISTER_in_instruction2323 = new BitSet(new long[]{0x0000000000000000L,0x0002000000000001L});
    public static final BitSet FOLLOW_offset_or_label_in_instruction2325 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_I_STATEMENT_FORMAT32x_in_instruction2339 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_INSTRUCTION_FORMAT32x_in_instruction2341 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_REGISTER_in_instruction2345 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_REGISTER_in_instruction2349 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_I_STATEMENT_FORMAT35c_METHOD_in_instruction2363 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_INSTRUCTION_FORMAT35c_METHOD_in_instruction2365 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_register_list_in_instruction2367 = new BitSet(new long[]{0x0000000000000000L,0x0000600000000000L});
    public static final BitSet FOLLOW_fully_qualified_method_in_instruction2370 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_I_STATEMENT_FORMAT35c_TYPE_in_instruction2384 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_INSTRUCTION_FORMAT35c_TYPE_in_instruction2386 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_register_list_in_instruction2388 = new BitSet(new long[]{0x0000000000000008L,0x0000680000000000L});
    public static final BitSet FOLLOW_nonvoid_type_descriptor_in_instruction2391 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_I_STATEMENT_FORMAT3rc_METHOD_in_instruction2405 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_INSTRUCTION_FORMAT3rc_METHOD_in_instruction2407 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_register_range_in_instruction2409 = new BitSet(new long[]{0x0000000000000000L,0x0000600000000000L});
    public static final BitSet FOLLOW_fully_qualified_method_in_instruction2412 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_I_STATEMENT_FORMAT3rc_TYPE_in_instruction2426 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_INSTRUCTION_FORMAT3rc_TYPE_in_instruction2428 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_register_range_in_instruction2430 = new BitSet(new long[]{0x0000000000000008L,0x0000680000000000L});
    public static final BitSet FOLLOW_nonvoid_type_descriptor_in_instruction2433 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_I_STATEMENT_FORMAT51l_in_instruction2447 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_INSTRUCTION_FORMAT51l_in_instruction2449 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_REGISTER_in_instruction2451 = new BitSet(new long[]{0x0439C00000000008L,0x0000000000000000L,0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_fixed_64bit_literal_in_instruction2453 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_I_STATEMENT_ARRAY_DATA_in_instruction2467 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_I_ARRAY_ELEMENT_SIZE_in_instruction2470 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_short_integral_literal_in_instruction2472 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_array_elements_in_instruction2475 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_I_STATEMENT_PACKED_SWITCH_in_instruction2489 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_I_PACKED_SWITCH_START_KEY_in_instruction2492 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_fixed_32bit_literal_in_instruction2494 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_packed_switch_targets_in_instruction2505 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_I_STATEMENT_SPARSE_SWITCH_in_instruction2519 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_sparse_switch_target_count_in_instruction2521 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000020000000L});
    public static final BitSet FOLLOW_sparse_switch_keys_in_instruction2523 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000080000000L});
    public static final BitSet FOLLOW_sparse_switch_targets_in_instruction2535 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_set_in_nonvoid_type_descriptor2562 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_reference_type_descriptor2591 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CLASS_DESCRIPTOR_in_class_type_descriptor2619 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VOID_TYPE_in_type_descriptor2635 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_nonvoid_type_descriptor_in_type_descriptor2642 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_long_literal_in_short_integral_literal2658 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_integer_literal_in_short_integral_literal2667 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_short_literal_in_short_integral_literal2676 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_char_literal_in_short_integral_literal2683 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_byte_literal_in_short_integral_literal2690 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_long_literal_in_integral_literal2704 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_integer_literal_in_integral_literal2713 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_short_literal_in_integral_literal2720 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_byte_literal_in_integral_literal2727 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INTEGER_LITERAL_in_integer_literal2742 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LONG_LITERAL_in_long_literal2756 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SHORT_LITERAL_in_short_literal2770 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BYTE_LITERAL_in_byte_literal2784 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLOAT_LITERAL_in_float_literal2798 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOUBLE_LITERAL_in_double_literal2812 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CHAR_LITERAL_in_char_literal2826 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_string_literal2840 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BOOL_LITERAL_in_bool_literal2856 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_I_ENCODED_ARRAY_in_array_literal2875 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_literal_in_array_literal2878 = new BitSet(new long[]{0x0579C00000000008L,0x0000780000000000L,0x00000000003E0000L,0x0000000000100000L});
    public static final BitSet FOLLOW_I_ANNOTATIONS_in_annotations2905 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_annotation_in_annotations2908 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_I_ANNOTATION_in_annotation2932 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ANNOTATION_VISIBILITY_in_annotation2934 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_subannotation_in_annotation2936 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_I_ANNOTATION_ELEMENT_in_annotation_element2954 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_SIMPLE_NAME_in_annotation_element2956 = new BitSet(new long[]{0x0579C00000000008L,0x0000780000000000L,0x00000000003E0000L,0x0000000000100000L});
    public static final BitSet FOLLOW_literal_in_annotation_element2958 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_I_SUBANNOTATION_in_subannotation2981 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_class_type_descriptor_in_subannotation2986 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_annotation_element_in_subannotation2992 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_I_ENCODED_FIELD_in_field_literal3019 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_fully_qualified_field_in_field_literal3021 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_I_ENCODED_METHOD_in_method_literal3039 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_fully_qualified_method_in_method_literal3041 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_I_ENCODED_ENUM_in_enum_literal3059 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_fully_qualified_field_in_enum_literal3061 = new BitSet(new long[]{0x0000000000000008L});

}