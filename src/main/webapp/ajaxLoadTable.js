$(document).ready(() => {
    /* without this var - script will not work, DON'T touch!
     * without transferring ${data[]} to ${arr[]} it will not work.
     */
    const seatList = [];
    $.ajax({
        type: 'GET',
        crossdomain: true,
        url: getContextPath() + '/hall.do',
    }).done((data) => {
        for (let i = 0; i < data.length; i++) {
            seatList.push(data[i]);
        }

        let rowNum = 1;
        let j = 0;
        let rsl = ``;
        for (let i = 0; i < seatList.length; i++) {
            let seat = seatList[i];
            // console.log(seatList[i])
            // console.log("j:", j)
            // console.log("if(j === 0):", (j === 0))
            if (j === 0) {
                rsl += `<tr><th>${rowNum}</th>`;
            }
            rsl += `
                <td><input id="seat-select" type="radio" name="seat" checked value=
                "row=${seat.row}&column=${seat.column}&hall=${seat.hallId}&id=${seat.id}">
                    Ряд: ${seat.row}
                    Место: ${seat.column}
                    Занято: ${seat.status}
                </td>`;

            // console.log("if(j === 2):", (j === 2))
            if (j === 2) {
                rsl += `</tr>`;
                // console.log("rsl:\n", rsl)
                $('#table tr:last').after(rsl);
                rowNum++;
                j = -1;
                rsl = '';
            }
            j++;
        }
    }).fail((err) => {
        alert("Error!!! - See console");
        console.log(err)
    })
});

function getContextPath() {
    return location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
}