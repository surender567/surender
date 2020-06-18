import { faList, faUsers } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import React, { Component } from 'react';
import { Button, ButtonGroup, Card, Table } from 'react-bootstrap';
import './Style.css';


export default class LibraryList extends Component {
    constructor(props) {
        super(props);
        this.state = {
            libraries: []
        };
    }

    componentDidMount() {
        this.findAllAvailableLibraries();
    }

    findAllAvailableLibraries() {
        let config = {
              method: 'GET',
              headers: {
                'Content-Type': 'application/json'
              }
            }
            fetch('http://localhost:9999/libraries', config)
              .then(response => response.json())
              .then(data => {
                this.setState({
                  libraries: data
                });
              })
              .catch(error => {
                console.log(error.message);
              })
    };

    bookList = () => {
        return this.props.history.push("/list");
    };


    render() {
        const { libraries } = this.state;


        return (
            <div>
                <Card className={"border border-dark bg-dark text-white"}>
                    <Card.Header><FontAwesomeIcon icon={faUsers} /> Library List</Card.Header>
                    <Card.Body>
                        <Table bordered hover striped variant="dark">
                            <thead>
                                <tr>
                                    <td>Name</td>
                                    <td>Address</td>
                                    <td>City</td>
                                    <td>State</td>
                                    <td>Postal Code</td>
                                    <td>Books Available</td>
                                </tr>
                            </thead>
                            <tbody>
                                {libraries.length === 0 ?
                                    <tr align="center">
                                        <td colSpan="6">No Library Available</td>
                                    </tr> :
                                    libraries.map((library, index) => (
                                        <tr key={index}>
                                            <td>{library.name}</td>
                                            <td>{library.address}</td>
                                            <td>{library.city}</td>
                                            <td>{library.stateOrProvince}</td>
                                            <td>{library.postalCode}</td>
                                            <td>
                                                <ButtonGroup>
                                                    <Button size="sm" variant="info" type="button" onClick={this.bookList.bind()}>
                                                        <FontAwesomeIcon icon={faList} /> Book List
                                                     </Button>
                                                </ButtonGroup>
                                            </td>
                                        </tr>
                                    ))
                                }
                            </tbody>
                        </Table>
                    </Card.Body>

                </Card>
            </div>
        );
    }
}