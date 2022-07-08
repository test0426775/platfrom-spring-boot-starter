package com.zxo.platform.tool;

import lombok.experimental.UtilityClass;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

/**
 * @ClassName: StringTools
 * @Author: zzzxxxooo
 * @Date: 2022/6/21 15:12
 * @Note:
 */
@UtilityClass
public class StringTools extends StringUtils {
    /**
     * Check whether the given {@code CharSequence} contains actual <em>text</em>.
     * <p>More specifically, this method returns {@code true} if the
     * {@code CharSequence} is not {@code null}, its length is greater than
     * 0, and it contains at least one non-whitespace character.
     * <pre class="code">
     * StringTools.isBlank(null) = true
     * StringTools.isBlank("") = true
     * StringTools.isBlank(" ") = true
     * StringTools.isBlank("12345") = false
     * StringTools.isBlank(" 12345 ") = false
     * </pre>
     *
     * @param cs the {@code CharSequence} to check (may be {@code null})
     * @return {@code true} if the {@code CharSequence} is not {@code null},
     * its length is greater than 0, and it does not contain whitespace only
     * @see Character#isWhitespace
     */
    public static boolean isBlank (@Nullable final CharSequence cs) {
        return !StringUtils.hasText(cs);
    }

    /**
     * <p>Checks if a CharSequence is not empty (""), not null and not whitespace only.</p>
     * <pre>
     * StringTools.isNotBlank(null)	  = false
     * StringTools.isNotBlank("")		= false
     * StringTools.isNotBlank(" ")	   = false
     * StringTools.isNotBlank("bob")	 = true
     * StringTools.isNotBlank("  bob  ") = true
     * </pre>
     *
     * @param cs the CharSequence to check, may be null
     * @return {@code true} if the CharSequence is
     * not empty and not null and not whitespace
     * @see Character#isWhitespace
     */
    public static boolean isNotBlank (@Nullable final CharSequence cs) {
        return StringUtils.hasText(cs);
    }
}
