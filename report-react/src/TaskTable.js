import React, {useState} from 'react';
import {
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Paper,
  TableFooter,
  TablePagination,
} from "@material-ui/core";

function TaskTable(tasks) {
  console.log(tasks)
  console.log(tasks.tasks[0])
  console.log(typeof(tasks));

  tasks = tasks.tasks

  const [page, setPage] = useState(0)
  const [rowsPerPage, setRowsPerPage] = useState(10)

  const handleChangePage = (event, newPage) => {
    setPage(newPage)
  }

  const handleChangeRowsPerPage = (event) => {
    setRowsPerPage(parseInt(event.target.value, 10))
    setPage(0)
  }

  return (
    <TableContainer component={Paper}>
      <Table size="small">
        <TableHead>
          <TableRow>
            <TableCell>No</TableCell>
            <TableCell align="left">TargetDate</TableCell>
            <TableCell align="left">WorkOut</TableCell>
            <TableCell align="left">Study</TableCell>
            <TableCell aligh="left">HangOut</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {tasks
            .slice(page * rowsPerPage, (page + 1) * rowsPerPage)
            .map(({id, targetDate, workOut, study, hangOut}, i) => (
              <TableRow key={id}>
                <TableCell component="th" scope="row">
                  {page * rowsPerPage + i + 1}
                </TableCell>
                <TableCell align="left">{targetDate}</TableCell>
                <TableCell align="left">{workOut}</TableCell>
                <TableCell align="left">{study}</TableCell>
                <TableCell align="left">{hangOut}</TableCell>
              </TableRow>
            ))}
        </TableBody>
        <TableFooter>
          <TableRow>
            <TablePagination
              count={tasks.length}
              page={page}
              rowsPerPage={rowsPerPage}
              onChangePage={handleChangePage}
              onChangeRowsPerPage={handleChangeRowsPerPage}
            />
          </TableRow>
        </TableFooter>
      </Table>
    </TableContainer>
  )
}

export default TaskTable;