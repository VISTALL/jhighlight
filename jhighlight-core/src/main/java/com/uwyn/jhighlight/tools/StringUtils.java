/*
 * Copyright 2001-2006 Geert Bevin <gbevin[remove] at uwyn dot com>
 * Distributed under the terms of either:
 * - the common development and distribution license (CDDL), v1.0; or
 * - the GNU Lesser General Public License, v2.1 or later
 * $Id: StringUtils.java 3108 2006-03-13 18:03:00Z gbevin $
 */
package com.uwyn.jhighlight.tools;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Pattern;

/**
 * General purpose class containing common <code>String</code> manipulation
 * methods.
 *
 * @author Geert Bevin (gbevin[remove] at uwyn dot com)
 * @version $Revision: 3108 $
 * @since 1.0
 */
public abstract class StringUtils
{

	private StringUtils()
	{
	}



	/**
	 * Checks if the name filters through a series of including and excluding
	 * regular expressions.
	 *
	 * @param name     The <code>String</code> that will be filtered.
	 * @param included An array of regular expressions that need to succeed
	 * @param excluded An array of regular expressions that need to fail
	 * @return <code>true</code> if the name filtered through correctly; or
	 *         <p/>
	 *         <code>false</code> otherwise.
	 * @since 1.0
	 */
	public static boolean filter(String name, Pattern[] included, Pattern[] excluded)
	{
		if(null == name)
		{
			return false;
		}

		boolean accepted = false;

		// retain only the includes
		if(null == included)
		{
			accepted = true;
		}
		else
		{
			Pattern pattern;
			for(int i = 0; i < included.length; i++)
			{
				pattern = included[i];

				if(pattern != null && pattern.matcher(name).matches())
				{
					accepted = true;
					break;
				}
			}
		}

		// remove the excludes
		if(accepted && excluded != null)
		{
			Pattern pattern;
			for(int i = 0; i < excluded.length; i++)
			{
				pattern = excluded[i];

				if(pattern != null && pattern.matcher(name).matches())
				{
					accepted = false;
					break;
				}
			}
		}

		return accepted;
	}

	/**
	 * Splits a string into different parts, using a seperator string to detect
	 * the seperation boundaries in a case-sensitive manner. The seperator will
	 * not be included in the list of parts.
	 *
	 * @param source    The string that will be split into parts.
	 * @param seperator The seperator string that will be used to determine the
	 *                  parts.
	 * @return An <code>ArrayList</code> containing the parts as
	 *         <code>String</code> objects.
	 * @since 1.0
	 */
	public static ArrayList split(String source, String seperator)
	{
		return split(source, seperator, true);
	}

	/**
	 * Splits a string into different parts, using a seperator string to detect
	 * the seperation boundaries. The seperator will not be included in the list
	 * of parts.
	 *
	 * @param source    The string that will be split into parts.
	 * @param seperator The seperator string that will be used to determine the
	 *                  parts.
	 * @param matchCase A <code>boolean</code> indicating if the match is going
	 *                  to be performed in a case-sensitive manner or not.
	 * @return An <code>ArrayList</code> containing the parts as
	 *         <code>String</code> objects.
	 * @since 1.0
	 */
	public static ArrayList split(String source, String seperator, boolean matchCase)
	{
		ArrayList substrings = new ArrayList();

		if(null == source)
		{
			return substrings;
		}

		if(null == seperator)
		{
			substrings.add(source);
			return substrings;
		}

		int current_index = 0;
		int delimiter_index = 0;
		String element = null;

		String source_lookup_reference = null;
		if(!matchCase)
		{
			source_lookup_reference = source.toLowerCase();
			seperator = seperator.toLowerCase();
		}
		else
		{
			source_lookup_reference = source;
		}

		while(current_index <= source_lookup_reference.length())
		{
			delimiter_index = source_lookup_reference.indexOf(seperator, current_index);

			if(-1 == delimiter_index)
			{
				element = new String(source.substring(current_index, source.length()));
				substrings.add(element);
				current_index = source.length() + 1;
			}
			else
			{
				element = new String(source.substring(current_index, delimiter_index));
				substrings.add(element);
				current_index = delimiter_index + seperator.length();
			}
		}

		return substrings;
	}

	/**
	 * Searches for a string within a specified string in a case-sensitive
	 * manner and replaces every match with another string.
	 *
	 * @param source            The string in which the matching parts will be replaced.
	 * @param stringToReplace   The string that will be searched for.
	 * @param replacementString The string that will replace each matching part.
	 * @return A new <code>String</code> object containing the replacement
	 *         result.
	 * @since 1.0
	 */
	public static String replace(String source, String stringToReplace, String replacementString)
	{
		return replace(source, stringToReplace, replacementString, true);
	}

	/**
	 * Searches for a string within a specified string and replaces every match
	 * with another string.
	 *
	 * @param source            The string in which the matching parts will be replaced.
	 * @param stringToReplace   The string that will be searched for.
	 * @param replacementString The string that will replace each matching part.
	 * @param matchCase         A <code>boolean</code> indicating if the match is going
	 *                          to be performed in a case-sensitive manner or not.
	 * @return A new <code>String</code> object containing the replacement
	 *         result.
	 * @since 1.0
	 */
	public static String replace(String source, String stringToReplace, String replacementString, boolean matchCase)
	{
		if(null == source)
		{
			return null;
		}

		if(null == stringToReplace)
		{
			return source;
		}

		if(null == replacementString)
		{
			return source;
		}

		Iterator string_parts = split(source, stringToReplace, matchCase).iterator();
		StringBuffer new_string = new StringBuffer();

		synchronized(new_string) // speed increase by thread lock pre-allocation
		{
			while(string_parts.hasNext())
			{
				String string_part = (String) string_parts.next();
				new_string.append(string_part);
				if(string_parts.hasNext())
				{
					new_string.append(replacementString);
				}
			}

			return new_string.toString();
		}
	}

	/**
	 * Creates a new string that contains the provided string a number of times.
	 *
	 * @param source The string that will be repeated.
	 * @param count  The number of times that the string will be repeated.
	 * @return A new <code>String</code> object containing the repeated
	 *         concatenation result.
	 * @since 1.0
	 */
	public static String repeat(String source, int count)
	{
		if(null == source)
		{
			return null;
		}

		StringBuffer new_string = new StringBuffer();
		synchronized(new_string) // speed increase by thread lock pre-allocation
		{
			while(count > 0)
			{
				new_string.append(source);
				count--;
			}

			return new_string.toString();
		}
	}

	/**
	 * Converts all tabs on a line to spaces according to the provided tab
	 * width.
	 *
	 * @param line     The line whose tabs have to be converted.
	 * @param tabWidth The tab width.
	 * @return A new <code>String</code> object containing the line with the
	 *         replaced tabs.
	 * @since 1.0
	 */
	public static String convertTabsToSpaces(String line, int tabWidth)
	{
		StringBuffer result = new StringBuffer();

		synchronized(result) // speed increase by thread lock pre-allocation
		{
			int tab_index = -1;
			int last_tab_index = 0;
			int added_chars = 0;
			int tab_size;
			while((tab_index = line.indexOf("\t", last_tab_index)) != -1)
			{
				tab_size = tabWidth - ((tab_index + added_chars) % tabWidth);
				if(0 == tab_size)
				{
					tab_size = tabWidth;
				}
				added_chars += tab_size - 1;
				result.append(line.substring(last_tab_index, tab_index));
				result.append(StringUtils.repeat(" ", tab_size));
				last_tab_index = tab_index + 1;
			}
			if(0 == last_tab_index)
			{
				return line;
			}
			else
			{
				result.append(line.substring(last_tab_index));
			}
		}

		return result.toString();
	}
}


