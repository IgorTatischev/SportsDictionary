package com.dictionary.sports.settings.presentation.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dictionary.sports.common.locale.AppLanguage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun LanguageSpinner(
    modifier: Modifier = Modifier,
    languages: List<AppLanguage>,
    selected: String,
    onClick: (AppLanguage) -> Unit,
) {
    var isExpanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = isExpanded,
        onExpandedChange = { isExpanded = it }) {

        OutlinedTextField(
            modifier = Modifier.menuAnchor(),
            readOnly = true,
            singleLine = true,
            value = selected,
            onValueChange = {},
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.background,
                unfocusedContainerColor = MaterialTheme.colorScheme.background,
                focusedTrailingIconColor = MaterialTheme.colorScheme.primary,
                unfocusedTrailingIconColor = MaterialTheme.colorScheme.primary,
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                cursorColor = MaterialTheme.colorScheme.primary,
            ),
            shape = RoundedCornerShape(20.dp)
        )

        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false }) {

            languages.forEach { item ->
                DropdownMenuItem(
                    text = { Text(text = item.name, color = MaterialTheme.colorScheme.background) },
                    onClick = {
                        onClick(item)
                        isExpanded = false
                    },
                )
            }
        }
    }
}

