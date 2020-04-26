import React from "react";
import logo from "./logo.svg";
import "./App.css";
import Header from "./components/Header";
import Navigation from "./components/Navigation";
import MainBody from "./components/MainBody";
import Footer from "./components/Footer";

const App = () => (
  <div className="App">
    <Header />
    <Navigation />
    <MainBody />
    <Footer />
  </div>
);

export default App;
