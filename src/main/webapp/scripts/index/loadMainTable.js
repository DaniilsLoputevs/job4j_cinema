$(document).ready(() => {
    $.ajax({
        type: 'GET',
        crossdomain: true,
        url: getContextPath() + '/hall.get',
    }).done((data) => {
        // console.log("data: ", data);

        /* used pipeline logic */
        let tableCells = mapDataToArrayTableCells(data);
        // console.log("tableCells", tableCells);
        let tableLines = mapTableCellsToTableLines(tableCells);
        // console.log("tableLines", tableLines);
        const finalHtml = mapTableLineToFinalHtml(tableLines);
        // console.log("finalHtml", finalHtml);
        document.getElementById("table-body").innerHTML = finalHtml;

    }).fail((err) => {
        alert("Error!!! - See console");
        console.log(err)
    })
});

function mapDataToArrayTableCells(data) {
    let rsl = [];
    let openTableCellTag = "<td>";
    let closeTableCellTag = "</td>";
    for (let i = 0; i < data.length; i++) {
        let seat = data[i];
        let startInnerTag = '';
        let finishInnerTag = '';

        if (seat.status === true) {
            startInnerTag = "<p>";
            finishInnerTag = "</p>";
        } else {
            startInnerTag = `<input id="seat-select" type="radio" name="seat" checked value=
                "row=${seat.row}&column=${seat.column}&hall=${seat.hallId}&id=${seat.id}">`;
            finishInnerTag = "</input> <br>";
        }
        let statusColor = (seat.status) ? "#f00" : "#2bc25b";

        let contentTag = ""
            + `Ряд: ${seat.row} `
            + `Место: ${seat.column} `
            // + `Занято: ${seat.status}`;
            + `Занято: <span style="color: ${statusColor};">${seat.status}</span>`;
        rsl[i] = openTableCellTag + startInnerTag
            + contentTag
            + finishInnerTag + closeTableCellTag;
    }
    return rsl;
}

/**
 * map 3 cells into 1 line.
 */
function mapTableCellsToTableLines(cells) {
    let rsl = [];
    let openLineTag = "<tr>";
    let closeLineTag = "</tr>";
    let rowCountMark = 1; // mark - mean: this let use only in HTML, no logic.
    let tagCounter = 0;

    while (tagCounter < cells.length) {
        rsl.push(openLineTag
            + `<th>${rowCountMark++}</th>`
            + mapTag(cells[tagCounter++])
            + mapTag(cells[tagCounter++])
            + mapTag(cells[tagCounter++])
            + closeLineTag);

    }
    return rsl;
}

function mapTag(tag) {
    return (tag !== undefined) ? tag : "";
}

function mapTableLineToFinalHtml(lines) {
    let rsl = "";
    for (let i = 0; i < lines.length; i++) {
        rsl += lines[i];
    }
    return rsl;
}