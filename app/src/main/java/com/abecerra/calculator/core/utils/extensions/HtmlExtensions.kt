package com.abecerra.calculator.core.utils.extensions

import android.os.Build
import android.text.Editable
import android.text.Html
import android.text.SpannableString
import android.text.Spanned
import org.xml.sax.XMLReader


fun fromHtmlCompat(html: String): Spanned {

    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(
            html.replace("<!--.*?-->", ""),
            Html.FROM_HTML_MODE_LEGACY, null, UlTagHandler()
        )
    } else {
        Html.fromHtml(html.replace("<!--.*?-->", ""))
    }
}


fun fromHtmlCompat(html: Spanned): Spanned =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(
            html.toString().replace("<!--.*?-->", ""),
            Html.FROM_HTML_MODE_LEGACY
        )
    } else {
        Html.fromHtml(html.toString().replace("<!--.*?-->", ""))
    }

fun fromHtmlCompat(html: SpannableString): Spanned =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(
            html.toString().replace("<!--.*?-->", ""),
            Html.FROM_HTML_MODE_LEGACY
        )
    } else {
        Html.fromHtml(html.toString().replace("<!--.*?-->", ""))
    }

class UlTagHandler : Html.TagHandler {
    override fun handleTag(opening: Boolean, tag: String, output: Editable, xmlReader: XMLReader) {
        if (tag == "ul" && !opening) output.append("\n")
        if (tag == "li" && opening) output.append("\n\t•")
    }
}