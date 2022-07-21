package com.github.wesbin.codegen.dialog.panel

import com.github.wesbin.codegen.dialog.util.StringUtil
import com.intellij.database.model.DasColumn
import com.intellij.database.model.DasObject
import com.intellij.database.util.DasUtil
import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.components.JBScrollPane
import com.intellij.ui.components.JBTextField
import com.intellij.ui.dsl.builder.panel
import com.intellij.ui.dsl.gridLayout.HorizontalAlign
import com.intellij.ui.dsl.gridLayout.VerticalAlign
import com.intellij.ui.table.JBTable
import com.intellij.util.ui.JBUI
import java.awt.BorderLayout
import javax.swing.JPanel
import javax.swing.ListSelectionModel
import kotlin.reflect.KProperty

@Suppress("UnstableApiUsage")
class RightPanel(val observableProperties: ObservableProperties): Panel, Observer {

    private val tableModel: ColumnTable = ColumnTable()

    private lateinit var classNameField: JBTextField

    override fun createPanel(): DialogPanel {

        val table = JBTable(tableModel)
            .apply {
                setShowGrid(false)
                intercellSpacing = JBUI.emptySize()
                dragEnabled = false
                showVerticalLines = false
                tableHeader.reorderingAllowed = false
                selectionModel.selectionMode = ListSelectionModel.SINGLE_SELECTION
            }

        return panel {
            row("Class name") {
                classNameField = textField()
                    .horizontalAlign(HorizontalAlign.FILL)
                    .component
            }
            row {
                cell(
                    JPanel(BorderLayout())
                        .apply {
                            add(JBScrollPane(table), BorderLayout.CENTER)
                        }
                )
                    .apply {
                        horizontalAlign(HorizontalAlign.FILL)
                        verticalAlign(VerticalAlign.FILL)
                    }
            }
                .resizableRow()

            // todo Dialog 수준으로 끌어올려야 한다 JBSplitter 3단은 안되는가? 애초에 상하단을 JBSplitter 로 분리해야 되는가?
            row {
                button("OK") {
                    println("OK")
                }
                    .component
                button("Cancel") {
                    println("Cancel")
                }
            }
        }
    }

    override fun update(property: KProperty<*>, newValue: Any?) {
        // fixme println remove
        println("RightPanel update")
        if (property.name == "selectedTable") {
            val selectedTable = newValue as DasObject?
            if (selectedTable != null) {
                classNameField.text = StringUtil.toPascalCase(selectedTable.name)
                tableModel.clear()
                DasUtil.getColumns(selectedTable).forEach { dasColumn: DasColumn? ->
                    if (dasColumn != null) {
                        tableModel.add(
                            ColumnRecordRaw(
                                dasColumn.name,
                                dasColumn.dataType.specification,
                                dasColumn.name,
                                dasColumn.dataType.specification
                            )
                        )
                    }
                }
            }
        }
    }
}

// 컬럼 크기 조절
//                            val width: Int = this.getFontMetrics(this.font).stringWidth("DIRECTORY") + 10
//                            columnModel.getColumn(0)
//                                .apply {
//                                    this.preferredWidth = width
//                                    this.maxWidth = width
//                                    this.minWidth = width
//                                }