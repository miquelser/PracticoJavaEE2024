<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:h="http://xmlns.jcp.org/jsf/html"
      xmlns:p="http://primefaces.org/ui">

<h:head>
    <title>Gestión de Hechos</title>
</h:head>
<h:body>
    <h:form>
        <p:panel header="Agregar Hecho">
            <p:panelGrid columns="2">
                <p:outputLabel for="fecha" value="Fecha:" />
                <p:calendar id="fecha" value="#{hechosBean.fecha}" pattern="dd/MM/yyyy" />
                
                <p:outputLabel for="descripcion" value="Descripción:" />
                <p:inputText id="descripcion" value="#{hechosBean.descripcion}" />
                
                <p:outputLabel for="calificacion" value="Calificación:" />
                <p:selectOneMenu id="calificacion" value="#{hechosBean.calificacion}">
                    <f:selectItem itemValue="#{org.example.models.Calificacion.Politica}" itemLabel="Política" />
                    <f:selectItem itemValue="#{org.example.models.Calificacion.Economía}" itemLabel="Economía" />
                    <f:selectItem itemValue="#{org.example.models.Calificacion.Tecnología}" itemLabel="Tecnología" />
                    <f:selectItem itemValue="#{org.example.models.Calificacion.Salud}" itemLabel="Salud" />
                    <f:selectItem itemValue="#{org.example.models.Calificacion.Deportes}" itemLabel="Deportes" />
                </p:selectOneMenu>

                <p:commandButton value="Agregar Hecho" action="#{hechosBean.agregarHecho}" update="hechosTable" />
            </p:panelGrid>
        </p:panel>

        <p:panel header="Buscar Hechos">
            <p:panelGrid columns="3">
                <p:outputLabel for="tipoBusqueda" value="Tipo:" />
                <p:selectOneMenu id="tipoBusqueda" value="#{hechosBean.tipoBusqueda}">
                    <f:selectItem itemValue="ID" itemLabel="ID" />
                    <f:selectItem itemValue="Descripcion" itemLabel="Descripción" />
                    <f:selectItem itemValue="Fecha" itemLabel="Fecha" />
                </p:selectOneMenu>
                
                <p:outputLabel for="valorBusqueda" value="Valor:" />
                <p:inputText id="valorBusqueda" value="#{hechosBean.valorBusqueda}" />
                
                <p:commandButton value="Buscar" action="#{hechosBean.buscarHechos}" update="hechosTable" />
            </p:panelGrid>
        </p:panel>

        <p:dataTable id="hechosTable" var="hecho" value="#{hechosBean.hechosList}" editable="true" paginator="true" rows="10">
            <p:column headerText="Número">
                <h:outputText value="#{hecho.numero}" />
            </p:column>
            <p:column headerText="Fecha">
                <h:outputText value="#{hecho.fecha}" />
            </p:column>
            <p:column headerText="Descripción">
                <h:outputText value="#{hecho.descripcion}" />
            </p:column>
            <p:column headerText="Calificación">
                <h:outputText value="#{hecho.calificacion}" />
            </p:column>
            <p:column headerText="Estado">
                <h:outputText value="#{hecho.estado}" />
            </p:column>
            <p:column headerText="Acciones">
                <p:commandButton value="Eliminar" action="#{hechosBean.eliminarHecho(hecho.numero)}" update="hechosTable" />
            </p:column>
        </p:dataTable>
    </h:form>
</h:body>
</html>