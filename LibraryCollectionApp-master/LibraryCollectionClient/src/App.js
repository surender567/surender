import React from 'react';
import './App.css';

import {Container, Row, Col} from 'react-bootstrap';
import {BrowserRouter as Router, Switch, Route} from 'react-router-dom';

import NavigationBar from './components/NavigationBar';
import Welcome from './components/Welcome';
import Book from './components/Book';
import BookList from './components/BookList';
import LibraryList from './components/LibraryList';
import Footer from './components/Footer';

export default function App() {

  const heading = "Welcome to Library Collection";
  const footer = "Surender Narlakanti";

  return (
    <Router>
        <NavigationBar/>
        <Container>
            <Row>
                <Col lg={12} className={"margin-top"}>
                    <Switch>
                        <Route path="/" exact component={() => <Welcome heading={heading}  footer={footer}/>}/>
                        <Route path="/add" exact component={Book}/>
                        <Route path="/edit/:id" exact component={Book}/>
                        <Route path="/list" exact component={BookList}/>
                        <Route path="/libraries" exact component={LibraryList}/>
                    </Switch>
                </Col>
            </Row>
        </Container>
        <Footer/>
    </Router>
  );
}
